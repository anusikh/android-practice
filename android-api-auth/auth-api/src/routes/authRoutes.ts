import express from "express";
import { Request, Response } from "express";
import User from "../models/User";
import {
  checkPassword,
  generateToken,
  getRefreshToken,
  hashPassword,
} from "../utils/jwt";
import jwtDecode from "jwt-decode";
import Token from "../models/Token";
import { validate } from "../middlewares/validate";
import {
  loginSchema,
  refreshSchema,
  registerSchema,
} from "../schemas/authSchema";
import { attachUser, requireAuth } from "../middlewares/attachUser";

const router = express.Router();

router.post(
  "/login",
  validate(loginSchema),
  async (req: Request, res: Response) => {
    const { email, password } = req.body;
    const user = await User.findOne({ email });

    if (!user) {
      return res.status(401).json({
        message: "user not found",
      });
    }

    const isPasswordValid = await checkPassword(password, user.password);

    if (!isPasswordValid) {
      return res.status(401).json({
        message: "invalid password",
      });
    }

    const accessToken = generateToken(user);
    const decodedToken = jwtDecode(accessToken);
    const expiresAt = (decodedToken as any).exp;
    const refreshToken = getRefreshToken();

    const existRefTok = await Token.findOne({ user: user._id });
    if (existRefTok) {
      await existRefTok.updateOne({ refreshToken });
    } else {
      const storedRefreshToken = new Token({ refreshToken, user: user._id });
      await storedRefreshToken.save();
    }

    res.json({
      accessToken,
      expiresAt: expiresAt,
      refreshToken,
    });
  }
);

router.post(
  "/register",
  validate(registerSchema),
  async (req: Request, res: Response) => {
    const { email, password, firstName, lastName } = req.body;
    const hashedPassword = await hashPassword(password);
    const userData = {
      ...req.body,
      password: hashedPassword,
    };

    const existingUser = await User.findOne({ email: email }).lean();

    if (existingUser) {
      return res.status(400).json({
        message: "email already exists",
      });
    }

    const user = new User(userData);
    const savedUser = await user.save();

    if (savedUser) {
      const accessToken = generateToken(user);
      const decodedToken = jwtDecode(accessToken);
      const expiresAt = (decodedToken as any).exp;

      return res.status(200).json({
        message: "user created successfully",
        accessToken,
        expiresAt,
      });
    }
  }
);

router.post(
  "/refresh",
  validate(refreshSchema),
  async (req: Request, res: Response) => {
    const { refreshToken } = req.body;
    try {
      const user = await Token.findOne({ refreshToken }).select("user");
      if (!user) {
        return res.status(401).json({
          message: "invalid token",
        });
      }

      const existUser = await User.findOne({ _id: user.user });
      if (!existUser) {
        return res.status(401).json({
          message: "invalid token",
        });
      }

      const token = generateToken(existUser);
      return res.json({ accessToken: token });
    } catch (err) {
      return res.status(500).json({ message: "couldn't refresh token" });
    }
  }
);

router.get(
  "/logged",
  [attachUser, requireAuth],
  (req: Request, res: Response) => {
    res.send({ status: "logged in" });
  }
);

export default router;

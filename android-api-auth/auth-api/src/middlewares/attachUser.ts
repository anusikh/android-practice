import { NextFunction, Request, Response } from "express";
import jwtDecode from "jwt-decode";
import { SECRET } from "../utils/jwt";
import { expressjwt } from "express-jwt";

export const attachUser = (req: Request, res: Response, next: NextFunction) => {
  const token = req.headers.authorization;
  if (!token) {
    return res.status(401).json({ message: "authorization token missing" });
  }
  const decodedToken = jwtDecode(token.slice(7));
  if (decodedToken) {
    (req as any).user = decodedToken;
    next();
  } else {
    return res
      .status(401)
      .json({ message: "there was a problem authorizing your token" });
  }
};

export const requireAuth = expressjwt({
  secret: SECRET,
  audience: "api.auth.com",
  issuer: "api.auth.com",
  algorithms: ["HS256"],
});

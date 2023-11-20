import jsonwebtoken from "jsonwebtoken";
import bcrypt from "bcrypt";
import randToken from "rand-token";
import { IUser } from "../models/User";

export const SECRET = "y!j=aw7+W!";

export const generateToken = (user: IUser) => {
  const token = jsonwebtoken.sign(
    {
      sub: user._id,
      email: user.email,
      aud: "api.auth.com",
      iss: "api.auth.com",
    },
    SECRET,
    { algorithm: "HS256", expiresIn: "1h" }
  );
  return token;
};

export const hashPassword = (password: string) => {
  return new Promise((resolve, reject) => {
    bcrypt.genSalt(10, (err, salt) => {
      if (err) reject(err);
      bcrypt.hash(password, salt, (err, hash) => {
        if (err) reject(err);
        resolve(hash);
      });
    });
  });
};

export const checkPassword = (password: string, hashedPassword: string) => {
  return bcrypt.compare(password, hashedPassword);
};

export const getRefreshToken = () => {
  return randToken.uid(256);
};

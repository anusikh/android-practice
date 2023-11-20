// storing all zod schemas here for auth route

import { z } from "zod";

export const registerSchema = z.object({
  body: z.object({
    email: z
      .string({ required_error: "email is required" })
      .email("not a valid email"),
    password: z.string({ required_error: "password is required" }),
    fullName: z.string({ required_error: "full name is required" }),
    lastName: z.string({ required_error: "last name is required" }),
  }),
});

export const loginSchema = z.object({
  body: z.object({
    email: z
      .string({ required_error: "email is required" })
      .email("not a valid email"),
    password: z.string({ required_error: "password is required" }),
  }),
});

export const refreshSchema = z.object({
  body: z.object({
    refreshToken: z.string({ required_error: "refresh token is required" }),
  }),
});

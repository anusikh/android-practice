import express, { Request, Response } from "express";
import cors from "cors";
import mongoose, { ConnectOptions } from "mongoose";
import authRoutes from "./routes/authRoutes";

const app = express();
const port = 5000;

app.use(cors());
app.use(express.urlencoded({ extended: false }));
app.use(express.json());

app.use("/auth", authRoutes);

app.get("/", (req: Request, res: Response) => {
  res.send({ name: "anusikh" });
});

const connect = async () => {
  try {
    mongoose.Promise = global.Promise;
    await mongoose.connect("mongodb://root:root@127.0.0.1:27017", {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    } as ConnectOptions);
  } catch (e) {
    console.log("mongoose error", e);
  }
  app.listen(port);
  console.log(`server listening on port ${port}`);
};

connect();

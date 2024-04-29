import { User } from "./user.model";

export interface Post {
  id: number;
  author: User;
  text: string;
  createdAt: string;
}

import { User } from "./user.model";

export interface Comment {
	id: number;
	author: User;
  text: string;
  createdAt: string;
}

import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../environment/environment";
import { PostsService } from "./posts.service";

@Injectable({
  providedIn: "root"
})
export class ImagesService {
  public static readonly BASE_PATH = "/images";

  constructor(private http: HttpClient,) {
  }

  public upload(postId: number, file: File): Observable<any> {
    const formData = new FormData();
    formData.append("file", file);
    return this.http.post(
      `${environment.apiPath.replace('5000', '5006')}${PostsService.BASE_PATH}/${postId}${ImagesService.BASE_PATH}`,
      formData);
  }

  public getImages(postId: number): Observable<any> {
    return this.http.get(
      `${environment.apiPath.replace('5000', '5006')}${PostsService.BASE_PATH}/${postId}${ImagesService.BASE_PATH}`);
  }
}

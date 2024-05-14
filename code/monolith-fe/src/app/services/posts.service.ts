import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { Post } from '../models/post.model';

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  static readonly BASE_PATH = '/posts';

  constructor(private http: HttpClient,) {
  }

  public getPosts(): Observable<any> {
    return this.http.get(`${environment.apiPath.replace('5000', '5002')}${PostsService.BASE_PATH}`);
  }

  public createPost(post: Post): Observable<any> {
    return this.http.post(`${environment.apiPath.replace('5000', '5002')}${PostsService.BASE_PATH}`, post);
  }
}

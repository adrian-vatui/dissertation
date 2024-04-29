import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { Post } from '../models/post.model';
import { Comment } from '../models/comment.model';

@Injectable({
  providedIn: 'root'
})
export class PostsService {
  static readonly BASE_PATH = '/posts';

  constructor(private http: HttpClient,) {
  }

  public getPosts(): Observable<any> {
    return this.http.get(`${environment.apiPath}${PostsService.BASE_PATH}`);
  }

  public createPost(post: Post): Observable<any> {
    return this.http.post(`${environment.apiPath}${PostsService.BASE_PATH}`, post);
  }

  public createComment(postId: number, comment: Comment): Observable<any> {
    return this.http.post(`${environment.apiPath}${PostsService.BASE_PATH}/${postId}/comments`, comment);
  }
}

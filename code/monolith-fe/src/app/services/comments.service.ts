import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { Comment } from '../models/comment.model';
import { PostsService } from './posts.service';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {
  public static readonly BASE_PATH = '/comments';

  constructor(private http: HttpClient,) {
  }

  public getComments(postId: number): Observable<any> {
    return this.http.get(`${environment.apiPath.replace('5000', '5004')}${PostsService.BASE_PATH}/${postId}${CommentsService.BASE_PATH}`)
  }

  public createComment(postId: number, comment: Comment): Observable<any> {
    return this.http.post(`${environment.apiPath.replace('5000', '5004')}${PostsService.BASE_PATH}/${postId}${CommentsService.BASE_PATH}`, comment);
  }
}

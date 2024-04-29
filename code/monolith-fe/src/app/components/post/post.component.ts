import { Component, Input, OnInit } from "@angular/core";
import { MatButton } from "@angular/material/button";
import {
  MatCard,
  MatCardAvatar,
  MatCardContent,
  MatCardHeader, MatCardMdImage,
  MatCardSmImage,
  MatCardTitle
} from "@angular/material/card";
import { MatFormField } from "@angular/material/form-field";
import { MatInput } from "@angular/material/input";
import { NgForOf, NgIf, NgOptimizedImage } from "@angular/common";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { Post } from "../../models/post.model";
import { CommentsService } from "../../services/comments.service";
import { Comment } from "../../models/comment.model";
import { MatIcon } from "@angular/material/icon";
import { Image } from "../../models/image";
import { ImagesService } from "../../services/images.service";

interface CommentForm {
  text: FormControl<string>;
}

@Component({
  selector: "app-post",
  standalone: true,
  imports: [
    MatButton,
    MatCard,
    MatCardAvatar,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatFormField,
    MatInput,
    NgForOf,
    ReactiveFormsModule,
    MatIcon,
    NgOptimizedImage,
    NgIf,
    MatCardSmImage,
    MatCardMdImage
  ],
  templateUrl: "./post.component.html",
  styleUrl: "./post.component.css"
})
export class PostComponent implements OnInit {
  @Input() post!: Post;
  displayComments = false;
  comments: Comment[] = [];
  newCommentForm = new FormGroup<CommentForm>(<CommentForm>{
    text: new FormControl("", { validators: [Validators.required] })
  });
  images: Image[] = [];

  constructor(private commentsService: CommentsService, private imagesService: ImagesService) {
  }

  ngOnInit() {
    this.imagesService.getImages(this.post.id).subscribe(images => this.images = images);
  }

  loadComments() {
    if (!this.displayComments) {
      this.commentsService.getComments(this.post.id).subscribe(comments => this.comments = comments);
    }
    this.displayComments = !this.displayComments;
  }

  createComment(post: Post): void {
    this.commentsService.createComment(post.id, { text: this.newCommentForm.value.text } as Comment)
      .subscribe((comment) => this.comments.unshift(comment));
  }
}

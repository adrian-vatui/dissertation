import { Component, OnInit } from "@angular/core";
import { PostsService } from "../../services/posts.service";
import { Post } from "../../models/post.model";
import { Comment } from "../../models/comment.model";
import { NgForOf, NgIf } from "@angular/common";
import { MatCard, MatCardAvatar, MatCardContent, MatCardHeader, MatCardTitle } from "@angular/material/card";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatDivider } from "@angular/material/divider";
import { MatFormField } from "@angular/material/form-field";
import { MatInput } from "@angular/material/input";
import { MatButton } from "@angular/material/button";
import { MatIcon } from "@angular/material/icon";
import { PostComponent } from "../post/post.component";
import { ImagesService } from "../../services/images.service";

interface PostForm {
  text: FormControl<string>;
}

@Component({
  selector: "app-home",
  standalone: true,
  imports: [
    NgForOf,
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardAvatar,
    MatCardContent,
    ReactiveFormsModule,
    MatDivider,
    NgIf,
    MatFormField,
    MatInput,
    MatButton,
    MatIcon,
    PostComponent
  ],
  templateUrl: "./home.component.html",
  styleUrl: "./home.component.css"
})
export class HomeComponent implements OnInit {
  posts?: Post[];
  newPostForm = new FormGroup<PostForm>(<PostForm>{
    text: new FormControl("", { validators: [Validators.required] })
  });
  selectedFiles?: FileList;

  constructor(private postsService: PostsService, private imageUploadService: ImagesService) {
  }

  ngOnInit(): void {
    this.postsService.getPosts().subscribe(posts => this.posts = posts);
  }

  createPost(): void {
    console.log(this.newPostForm.value);
    this.postsService.createPost({ text: this.newPostForm.value.text } as Post)
      .subscribe((post) => {
        if (this.selectedFiles) {
          Array.from(this.selectedFiles).forEach(file => {
            this.imageUploadService.upload(post.id, file).subscribe();
          });
        }
        this.posts?.unshift(post);
      });
  }

  selectFiles(event: any): void {
    this.selectedFiles = event.target.files;
    console.log(this.selectedFiles);
  }
}

import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButton, MatIconButton } from '@angular/material/button';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';
import { MatFormField } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatInput } from '@angular/material/input';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    FormsModule,
    MatButton,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatFormField,
    MatIcon,
    MatIconButton,
    MatInput,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  public hide = true;
  public signupForm: FormGroup = new FormGroup<any>({
    username: new FormControl('', { validators: [Validators.required] }),
    email: new FormControl('', { validators: [Validators.required, Validators.email] }),
    password: new FormControl('', { validators: [Validators.required] })
  });

  constructor(private authService: AuthenticationService,
              private storageService: StorageService,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  public ngOnInit(): void {
  }

  public signup(): void {
    if (!this.signupForm.valid) {
      return;
    }
    this.performSignup();
  }

  public performSignup(): void {

    this.authService
      .signup(
        {
          username: this.signupForm.controls['username'].value,
          email: this.signupForm.controls['email'].value,
          password: this.signupForm.controls['password'].value
        }
      )
      .subscribe({
          next: data => {
            this.router.navigate(['/login']);
          },
          error: err => {

          }
        }
      );
  }

  public disableButton(): boolean {
    return this.signupForm.valid;
  }
}

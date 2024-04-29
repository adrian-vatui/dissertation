import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { MatCard, MatCardContent, MatCardHeader, MatCardTitle } from '@angular/material/card';
import { MatFormField } from '@angular/material/form-field';
import { MatIcon } from '@angular/material/icon';
import { MatButton, MatIconButton } from '@angular/material/button';
import { MatInput } from '@angular/material/input';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    MatCardContent,
    MatFormField,
    ReactiveFormsModule,
    MatIcon,
    MatIconButton,
    MatInput,
    MatButton,
    RouterLink
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public hide = true;
  public loginForm: FormGroup = new FormGroup<any>({
    username: new FormControl('', { validators: [Validators.required] }),
    password: new FormControl('', { validators: [Validators.required] })
  });

  constructor(private authService: AuthenticationService,
              private storageService: StorageService,
              private router: Router,
              private route: ActivatedRoute,) {
  }

  public ngOnInit(): void {
  }

  public login(): void {
    if (!this.loginForm.valid) {
      return;
    }
    this.performLogin();
  }

  public performLogin(): void {

    this.authService
      .login(
        {
          username: this.loginForm.controls['username'].value,
          password: this.loginForm.controls['password'].value
        }
      )
      .subscribe({
          next: data => {
            this.storageService.saveUser(data);
            const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
            this.router.navigate([returnUrl]);
          },
          error: err => {

          }
        }
      );
  }

  public disableButton(): boolean {
    return this.loginForm.valid;
  }
}

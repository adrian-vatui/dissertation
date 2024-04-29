import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginRequest, SignupRequest } from '../models/auth.model';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public static readonly BASE_PATH = '/auth';

  constructor(private http: HttpClient,) {
  }

  public login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post(`${environment.apiPath}${AuthenticationService.BASE_PATH}/login`, loginRequest);
  }

  public signup(signupRequest: SignupRequest): Observable<any> {
    return this.http.post(`${environment.apiPath}${AuthenticationService.BASE_PATH}/signup`, signupRequest);
  }

  public logout(): Observable<any> {
    return this.http.post(`${environment.apiPath}${AuthenticationService.BASE_PATH}/logout`, null);
  }
}

import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private static readonly USER_KEY = 'auth-user';

  constructor() {
  }

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: User): void {
    window.sessionStorage.removeItem(StorageService.USER_KEY);
    window.sessionStorage.setItem(StorageService.USER_KEY, JSON.stringify(user));
  }

  public getUser(): User {
    const user = window.sessionStorage.getItem(StorageService.USER_KEY);
    return user ? JSON.parse(user) : {};

  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(StorageService.USER_KEY);
    return !!user;
  }
}

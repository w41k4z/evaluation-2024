import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

  isAuthenticated(): boolean {
    return localStorage.getItem('token') ? true : false;
  }

  authenticate(token: string): void {
    localStorage.setItem('token', token);
  }
}

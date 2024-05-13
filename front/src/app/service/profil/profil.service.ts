import { Injectable } from '@angular/core';
import * as jwt from 'jwt-decode';
import { AuthService } from '../auth/auth.service';
import { BTP, CLIENT } from '../../util/Profil';

@Injectable({
  providedIn: 'root',
})
export class ProfilService {
  constructor(private authService: AuthService) {}

  isBTP() {
    const token = localStorage.getItem('token');
    if (this.authService.isAuthenticated() && token) {
      const deserializedToken: any = jwt.jwtDecode(token);
      return JSON.parse(deserializedToken.userDetails).authority == BTP;
    }
    return false;
  }

  isClient() {
    const token = localStorage.getItem('token');
    if (this.authService.isAuthenticated() && token) {
      const deserializedToken: any = jwt.jwtDecode(token);
      return JSON.parse(deserializedToken.userDetails).authority == CLIENT;
    }
    return false;
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { env } from '../../../environment/env';

@Injectable({
  providedIn: 'root',
})
export class SignUpService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  private errorSubject = new BehaviorSubject<string | null>(null);
  public error$ = this.errorSubject.asObservable();

  constructor(public http: HttpClient) {}

  register(
    email: string | undefined | null,
    password: string | undefined | null
  ): Observable<any> {
    this.loadingSubject.next(true);
    return this.http
      .post(`${env.api}/auth/sign-up`, {
        username: email,
        password: password,
      })
      .pipe(
        tap(() => this.loadingSubject.next(false)),
        catchError((error) => {
          this.loadingSubject.next(false);
          console.log(error);
          this.errorSubject.next(error.error ? error.error : error.message);
          return throwError(() => error);
        })
      );
  }
}

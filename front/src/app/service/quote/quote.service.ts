import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { env } from '../../../environment/env';

@Injectable({
  providedIn: 'root',
})
export class QuoteService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  private errorSubject = new BehaviorSubject<string | null>(null);
  public error$ = this.errorSubject.asObservable();

  constructor(public http: HttpClient) {}

  fetchQuotes(): Observable<any> {
    this.loadingSubject.next(true);
    return this.http.get(`${env.api}/api/quotes/client`).pipe(
      tap(() => this.loadingSubject.next(false)),
      catchError((error) => {
        this.loadingSubject.next(false);
        console.log(error);
        this.errorSubject.next(error.error ? error.error : error.message);
        return throwError(() => error);
      })
    );
  }

  fetchQuote(quoteId: string | null): Observable<any> {
    this.loadingSubject.next(true);
    return this.http.get(`${env.api}/api/quotes/${quoteId}`).pipe(
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

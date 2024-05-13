import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap } from 'rxjs';
import { env } from '../../../environment/env';
import { HouseType } from '../../model/HouseType';
import { FinitionType } from '../../model/FinitionType';

@Injectable({
  providedIn: 'root',
})
export class NewQuoteService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  private errorSubject = new BehaviorSubject<string | null>(null);
  public error$ = this.errorSubject.asObservable();

  constructor(public http: HttpClient) {}

  fetchHouseTypesAndFinitionTypes(): Observable<any> {
    this.loadingSubject.next(true);
    return this.http.get(`${env.api}/api/quotes/house-types`).pipe(
      tap(() => this.loadingSubject.next(false)),
      catchError((error) => {
        this.loadingSubject.next(false);
        this.errorSubject.next(error.message);
        return error;
      })
    );
  }

  newQuote(
    finitionType: FinitionType | null,
    houseType: HouseType | null,
    constructionStartDate: Date
  ) {
    if (finitionType && houseType) {
      this.loadingSubject.next(true);
      return this.http
        .post(`${env.api}/api/quotes/house-types`, {
          finitionType: finitionType,
          houseType: houseType,
          constructionStartDate: constructionStartDate,
        })
        .pipe(
          tap(() => this.loadingSubject.next(false)),
          catchError((error) => {
            this.loadingSubject.next(false);
            this.errorSubject.next(error.message);
            return error;
          })
        );
    }
    alert('Le type de maison et finition sont requise');
    throw new Error('Le type de maison et finition sont requise');
  }
}

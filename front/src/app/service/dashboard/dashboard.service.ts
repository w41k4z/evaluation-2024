import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { env } from '../../../environment/env';
import { BehaviorSubject, catchError, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();

  constructor(public http: HttpClient) {}

  reinitialize() {
    this.loadingSubject.next(true);
    return this.http.get(`${env.api}/admin/reinitialize-database`).pipe(
      tap(() => this.loadingSubject.next(false)),
      catchError((error) => {
        this.loadingSubject.next(false);
        alert('Error: ' + error.message);
        return error;
      })
    );
  }
}

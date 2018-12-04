import { Injectable } from '@angular/core';
import { ReceivedQuery } from './models/receivedQuery';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { ReceivedQuery2 } from './models/received-query2';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})

export class DataService {
  // change to IP address of query-engine
  private _url = 'http://localhost:8185/api/v1/results';

  constructor(private http: HttpClient) { }

  getQuery(): Observable<ReceivedQuery2> {
    console.log(this.http.get<ReceivedQuery2>('recieved data: ' + this._url));
    return this.http.get<ReceivedQuery2>(this._url).pipe(catchError(err => {
      console.log(err.error);
      return throwError(err.error);
    }));
  }
}
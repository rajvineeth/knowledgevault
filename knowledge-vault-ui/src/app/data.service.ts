import { Injectable } from '@angular/core';
import { iReceivedQuery } from './receivedQuery';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpHeaders, HttpErrorResponse } from '@angular/common/http';


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
  private _url: string = "http://localhost:0000/query";

  constructor(private http: HttpClient) { }

  getQuery(): Observable<iReceivedQuery[]>{
    console.log(this.http.get<iReceivedQuery[]>(this._url));
    return this.http.get<iReceivedQuery[]>(this._url).pipe(catchError(err => {
      console.log(err.error);
      return throwError(err.error)
    }));
  }
}

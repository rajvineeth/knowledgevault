import { Injectable } from '@angular/core';
import { ReceivedQuery } from './models/receivedQuery';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
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
  private _url = 'http://35.154.72.182:8090/query-engine/api/v1/results';

  constructor(private http: HttpClient) { }

  getQuery(): Observable<Array<ReceivedQuery>> {
    // console.log(this.http.get('recieved data: ' + this._url));
    return this.http.get<Array<ReceivedQuery>>(this._url);
  }
}
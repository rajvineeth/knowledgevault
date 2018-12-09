import { Injectable } from '@angular/core';
import { ReceivedQuery } from './models/receivedQuery';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class DataService {
  // change to IP address of query-engine
  private _url = 'https://KnowledgeVault-zuul.stackroute.in/query-engine/api/v1/results';
  // private _url = 'http://172.23.239.143:8185/api/v1/results';

  constructor(private http: HttpClient) { }

  getQuery(): Observable<Array<ReceivedQuery>> {

    const token = localStorage.getItem('usertoken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    // console.log(this.http.get('recieved data: ' + this._url));
    return this.http.get<Array<ReceivedQuery>>(this._url, httpOptions);
  }
}

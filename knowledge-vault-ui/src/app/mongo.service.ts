import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})

export class MongoService {

  private fetchUrl = 'http://localhost:8181/api/v1/user/';

  constructor(private http: HttpClient) { }

  fetchUserData(name: string, token: string): Observable<any> {

    console.log('token: ', token);
    const httpoption = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + token
      })
    };

    return this.http.get(this.fetchUrl + name, httpoption);
  }
}

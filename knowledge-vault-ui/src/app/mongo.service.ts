import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})

export class MongoService {

  private fetchUrl = 'https://KnowledgeVault-zuul.stackroute.in/user-registration-service/api/v1/user/';

  constructor(private http: HttpClient) { }

  fetchUserData(name: string, token: string): Observable<any> {

    console.log('token for mongo access: ', localStorage.getItem('usertoken'));
    const httpoption = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };

    return this.http.get(this.fetchUrl + name, httpoption);
  }
}

import { ShareService } from './share.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from './models/auth/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private loginUrl = 'http://localhost:8184/user/login';
  private validateURL = 'http://localhost:8184/secure/user/';

  constructor(private http: HttpClient, private router: Router, private srvc: ShareService) { }

  public login(user: User): Observable<any> {
    return this.http.post<any>(this.loginUrl, user, httpOptions);
  }

  // public validateUser(user: User): Observable<any> {
  //   this.login(user);
  //   const url: string = this.validateURL + user.username;
  //   console.log(localStorage.getItem('userdata'));
  //   const httpoption = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json',
  //       'Access-Control-Allow-Origin': '*',
  //       'Authorization': 'Bearer ' + localStorage.getItem('currentuser')
  //     })
  //   };
  //   return this.http.get(url, httpoption);
  // }
}

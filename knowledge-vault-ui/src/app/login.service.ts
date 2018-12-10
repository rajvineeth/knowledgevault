import { ShareService } from './share.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from './models/auth/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  private loginUrl = 'https://KnowledgeVault-zuul.stackroute.in/user-authentication/user/login';
  private validateURL = 'https://KnowledgeVault-zuul.stackroute.in/user-authentication/secure/user/';

  constructor(private http: HttpClient, private router: Router, private srvc: ShareService) { }

  public login(user: User): Observable<any> {
    return this.http.post<any>(this.loginUrl, user, httpOptions);
  }
}

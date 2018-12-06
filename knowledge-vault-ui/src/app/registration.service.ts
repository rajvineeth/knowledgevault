import { Observable } from 'rxjs/internal/Observable';
import { UserDetails } from './models/reg/userdetails';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})

export class RegistrationService {

  private registerUrl = 'https://KnowledgeVault-zuul.stackroute.in/user-registration-service/api/v1/saveuser';

  constructor(private http: HttpClient) { }

  public registerUser(fn: string, ln: string, username: string, role: string, pass: string): Observable<any> {
    const details = new UserDetails(fn, ln, username, role, pass);
    return this.http.post(this.registerUrl, details, httpOptions);
  }
}

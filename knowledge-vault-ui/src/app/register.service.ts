import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './_models';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  private registerUrl = 'http://localhost:8181/api/v1/saveuser';

  constructor(private http: HttpClient) { }

  saveUser(user: User) {
    this.http.post(this.registerUrl, httpOptions);
  }
}

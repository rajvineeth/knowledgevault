import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})

export class LoginService {
  
  private loginUrl: string = 'http://localhost:8184/user/login'

  constructor(private http: HttpClient) { }

  

}

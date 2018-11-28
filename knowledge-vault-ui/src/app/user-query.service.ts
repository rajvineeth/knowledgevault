import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserQueryService {

  private getRequest = 'http://localhost:8081/input-kafka/kv/';

  constructor(private http: HttpClient) { }

  postUserQuery(inputText: string) {
    const getUrl = this.getRequest + inputText;
    this.http.get<string>(getUrl, httpOptions).subscribe(data => { console.log(data); });
  }
}

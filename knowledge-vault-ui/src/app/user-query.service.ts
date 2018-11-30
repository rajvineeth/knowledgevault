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

  // nlp-pipeline service url-path
  private getRequest: string = 'http://172.23.239.127:8148/kv/';

  constructor(private http: HttpClient) { }

  postUserQuery(inputText: string) {
    let getUrl = this.getRequest + inputText;
    this.http.get<string>(getUrl,httpOptions)
      .subscribe(
        data => {
         if(data == null) console.log(data); 
        }
      );
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { of } from 'rxjs';
import { ReceivedQuery } from './models/receivedQuery';

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
  private getRequest = 'https://KnowledgeVault-zuul.stackroute.in/input-kafka/kv/';
  // private getRequest = 'http://172.23.239.143:8148/kv/';

  getDisease(medicalConditionId: number): any {
    return this.http.get<ReceivedQuery>(this.getRequest + medicalConditionId);
  }

  constructor(private http: HttpClient) { }

  postUserQuery(inputText: string) {
    const getUrl = this.getRequest + inputText;
    this.http.get<string>(getUrl, httpOptions)
      .subscribe(
        data => {
          if (data == null) {
            console.log(data);
          }
        }
      );
  }
}

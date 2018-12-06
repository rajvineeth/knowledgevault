import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class CrawlerService {

  private crawlerUrl = 'https://KnowledgeVault-zuul.stackroute.in/web-adapter/api/v1/webscraper';

  constructor(private http: HttpClient) { }

  scrapeUrl(url:string) : Observable<any>{
    const httpOption = {
      headers: new HttpHeaders({
        'Content-Type': 'text'
        // 'Authorisation': 'Bearer '+ token
      })
    };
    return this.http.post(this.crawlerUrl,url,httpOption);
  }
}

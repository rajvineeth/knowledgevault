import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { UrlClass } from './models/urlClass';

const httpOption = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})

export class CrawlerService {

  private crawlerUrl = 'https://KnowledgeVault-zuul.stackroute.in/web-adapter/api/v1/webscraper';

  constructor(private http: HttpClient) { }

  scrapeUrl (url: UrlClass) {
    this.http.post(this.crawlerUrl, url, httpOption).subscribe(
      data => {
        console.log(data);
      }
    );
  }
}

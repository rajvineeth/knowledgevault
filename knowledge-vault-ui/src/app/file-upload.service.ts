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
export class FileUploadService {

  private getRequest = 'https://KnowledgeVault-zuul.stackroute.in/extractor-service/api/';
//  private postRequest = 'http://localhost:8094/api/';

  constructor(private http: HttpClient) { }

  extractFile(files: File[]) {
      const getUrl = this.getRequest + files;
      this.http.get<any>(getUrl, httpOptions)
      .subscribe(
        data => {
          //if (data == null) {
            console.log(data);
            console.log("sent data");
        }
      );
  }
  }


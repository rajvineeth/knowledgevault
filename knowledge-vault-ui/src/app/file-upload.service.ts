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

//  private postRequest = 'https://KnowledgeVault-zuul.stackroute.in/extractor-service/api/';
  private postRequest = 'http://localhost:8094/api/';

  constructor(private http: HttpClient) { }

  extractFile(files: File[]) {
      const postUrl = this.postRequest + files;
      this.http.post<File[]>(postUrl, httpOptions)
      .subscribe(
        data => {
          //if (data == null) {
            console.log(data);
        }
      );
  }
  }


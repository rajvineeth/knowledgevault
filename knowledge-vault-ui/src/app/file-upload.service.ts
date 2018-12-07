import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    //    'Content-Type': 'application/json'
    'Content-Type': 'multipart/form-data;boundary=' + Math.random(),
    'Accept': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private getRequest = 'https://KnowledgeVault-zuul.stackroute.in/extractor-service/api/';
  //  private postRequest = 'http://localhost:8094/api/';

  constructor(private http: HttpClient) { }

  extractFile(formData: FormData) {
    const getUrl = this.getRequest + 'Medical';
    console.log('in service');
    console.log(formData.getAll('File'));
    this.http.post(this.getRequest, formData, httpOptions);
    //      console.log('dsfds'+this.http.post(this.getRequest, formData, httpOptions));
    // .subscribe(
    //   data => {
    //     //if (data == null) {
    //       console.log(data);
    //       console.log("sent data");
    //   }
    // );
  }
}


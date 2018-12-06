import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'multipart/form-data',
    'Accept': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private getRequest = 'http://35.154.72.182:8094/api/upload';
//  private postRequest = 'http://localhost:8094/api/';

  constructor(private http: HttpClient) { }

  extractFile(formData: FormData) {
//      const getUrl = this.getRequest + files;
      console.log('in service');
      console.log(formData.getAll('File'));
      console.log('dsfds'+this.http.post(this.getRequest, formData, httpOptions));
      // .subscribe(
      //   data => {
      //     //if (data == null) {
      //       console.log(data);
      //       console.log("sent data");
      //   }
      // );
  }
  }


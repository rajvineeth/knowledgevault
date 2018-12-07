import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Injectable } from '@angular/core';

// const httpOptions = {
//   headers: new HttpHeaders({
// //    'Content-Type': 'application/json'
//         'Content-Type': 'multipart/form-data;boundary='+Math.random(),
//     'Accept': 'application/json'
//   })
// };

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private getRequest = 'http://35.154.72.182:8094/api/upload';
//  private postRequest = 'http://localhost:8094/api/';

  constructor(private http: HttpClient) { }

  extractFile(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', '/upload', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
//      const getUrl = this.getRequest + 'Medical';
      // console.log('in service');
      // console.log(formData.getAll('File'));
      // return this.http.post(this.getRequest, formData, httpOptions);
      // .subscribe(
      //   data => {
      //     if (data == null) {
      //       console.log(data);
      //     }
      //   }
      // );
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
}


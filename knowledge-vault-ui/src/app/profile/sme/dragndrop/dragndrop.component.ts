import { FileUploadService } from './../../../file-upload.service';
import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpEventType } from '@angular/common/http';

@Component({
//  selector: 'app-dragndrop',
selector: 'app-dragndrop',
  templateUrl: './dragndrop.component.html',
  styleUrls: ['./dragndrop.component.css']
})

export class DragndropComponent implements OnInit {

  constructor(private upload_service: FileUploadService) {
  }

  fileList = [];
  FileName: string;
  save_success: string;
  show_success_msg = false;
//  formData: FormData = new FormData();
  IsUploaded = false;
  progress: { percentage: number } = { percentage: 0 };

  ngOnInit() { }

  //#region file upload drag and drop on intake page
  sendFileDB() {
    this.IsUploaded = false;
    this.show_success_msg = true;
    this.save_success = "Thank you for your contribution.We have saved your document in our database.We will add it to our knowledge-base once it is approved by Paurush Chaudhary."
    if (this.fileList.length > 0) {

      for (let i = 0; i < this.fileList.length; i++) {
        this.progress.percentage = 0;
        this.upload_service.extractFile(this.fileList[i]).subscribe(event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progress.percentage = Math.round(100 * event.loaded / event.total);
          } else if (event instanceof HttpResponse) {
            console.log('File is completely uploaded!');
          }
        });
//        this.FileName = this.fileList[i].name;
//        console.log(this.fileList[i]);
//        this.formData.append('File', this.fileList[i]);
//        console.log(this.formData.getAll('File'));
        
        // append more item in FormData and send to server
        // call service to send file on server via FormData.

          // if file saved in DB set true.
        // this.IsUploaded = true;
      }
      // console.log(this.formData.getAll('File').valueOf());
      // this.upload_service.extractFile(this.formData);
      //console.log(this.formData.getAll('File'));
    }
  }
  getFiles(files) {
    // all file are avilable here.you can customize according your rquirment.
    if (files !== false && files !== undefined && files != null && files !== '' && files.length > 0) {
      this.fileList = files;
    } else if (files == null) {
      this.fileList = [];
      return;
    }
  }
}

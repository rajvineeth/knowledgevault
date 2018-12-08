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

  constructor(private upload_service: FileUploadService) {}

  fileList = [];
  FileName: string;
  save_success: string;
  show_success_msg = false;
  IsUploaded = false;
  progress: { percentage: number } = { percentage: 0 };

  ngOnInit() { }

  //#region file upload drag and drop on intake page
  sendFileDB() {
    this.IsUploaded = false;
    this.show_success_msg = true;

    alert(this.save_success = 'Thank you for your contribution.We have saved your document in our database.');
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
      }

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

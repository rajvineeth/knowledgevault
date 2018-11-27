import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dragndrop',
  templateUrl: './dragndrop.component.html',
  styleUrls: ['./dragndrop.component.css']
})

export class DragndropComponent implements OnInit {

  constructor() {

  }

  fileList = [];
  FileName: string;
  save_success: string;
  show_success_msg = false;
  formData: FormData = new FormData();
  IsUploaded = false;

  ngOnInit() { }

  //#region file upload drag and drop on intake page
  sendFileDB() {
    this.IsUploaded = false;
    this.show_success_msg = true;
<<<<<<< HEAD
    this.save_success = "Thank you for your contribution.We have saved your document in our database.We will add it to our knowledge-base once it is approved by Paurush Chaudhary."
=======
    this.save_success = 'thank you for your contribution. We will add it to knowledge-base once it is approved.';
>>>>>>> 5fcbca88d6034e53b7d42b7c1826c3e5c20c0685
    if (this.fileList.length > 0) {
      for (let i = 0; i < this.fileList.length; i++) {
        this.FileName = this.fileList[i].name;
        this.formData.append('File', this.fileList[i]);
        // append more item in FormData and send to server
        // call service to send file on server via FormData.

          // if file saved in DB set true.
        // this.IsUploaded = true;
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

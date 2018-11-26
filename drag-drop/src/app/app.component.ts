import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor() {

  }
  ngOnInit() { }
  fileList = [];
  FileName: string;
  formData: FormData = new FormData();
  IsUploaded: boolean = false;
  //#region file upload drag and drop on intake page
  sendFileDB() {
    this.IsUploaded = false;
    if (this.fileList.length > 0) {
      for (let i = 0; i < this.fileList.length; i++) {
        this.FileName = this.fileList[i].name;
        this.formData.append("File", this.fileList[i]);
        // append more item in FormData and send to server
        //call service to send file on server via FormData.
        
          // if file saved in DB set true.
        //this.IsUploaded = true;
      }
    }
  }
  getFiles(files) {
    //all file are avilable here.you can customize according your rquirment.
    if (files != false && files != undefined && files != null && files != '' && files.length > 0) {
      this.fileList = files;
    }
    else if (files == null) {
      this.fileList = [];
      return;
    }
  }
  //#endregion
}

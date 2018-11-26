import { Component, OnInit, Input, Output, EventEmitter, HostListener } from '@angular/core';
// import { FileService } from '../file.service';
@Component({
  selector: 'file-upload-drag-drop',
  templateUrl: './file-upload-drag-drop.component.html',
  styleUrls: ['./file-upload-drag-drop.component.css']
})
export class FileUploadComponent implements OnInit {
  errors: Array<string> = [];
  successfiles: Array<string> = [];
  dragAreaClass: string = 'dragarea';
  fileList = [];
  @Input() fileExt: string = "PDF";
  @Input() maxFiles: number = 10;
  @Input() maxSize: number = 10; // 10MB
  @Output() uploadStatus = new EventEmitter<any>();
  @Input() set IsuploadSucess(isUploaded: boolean) {
    if (isUploaded) {
      this.errors = [];
      this.successfiles = [];
      this.fileList = [];
    }
  }
  constructor() {
    this.successfiles = [];
  }
  ngOnInit() { }
  onFileChange(event) {
    let files = event.target.files;
    this.saveFiles(files);
  }
  @HostListener('dragover', ['$event']) onDragOver(event) {
    this.dragAreaClass = "droparea";
    event.preventDefault();
  }
  @HostListener('dragenter', ['$event']) onDragEnter(event) {
    this.dragAreaClass = "droparea";
    event.preventDefault();
  }
  @HostListener('dragleave', ['$event']) onDragLeave(event) {
    this.dragAreaClass = "dragarea";
    event.preventDefault();
  }
  @HostListener('drop', ['$event']) onDrop(event) {
    this.dragAreaClass = "dragarea";
    event.preventDefault();
    event.stopPropagation();
    var files = event.dataTransfer.files;
    this.saveFiles(files);
  }
  saveFiles(files) {
    this.errors = [];
    // Validate file size and allowed extensions
    if (files.length > 0 && (!this.isValidFiles(files))) {
      this.uploadStatus.emit(false);
      return;
    }
    if (files.length > 0 && (this.isValidFiles(files))) {
      for (var j = 0; j < files.length; j++) {
        if (!this.fileList.some(x => x.name == files[j].name)) {
          this.fileList.push(files[j]);
          this.successfiles.push("File: " + files[j].name + " added successfully.");
        }
        else {
          this.errors.push("File: " + files[j].name + " Already added in list.");
        }
      }
      this.uploadStatus.emit(this.fileList);
      return;
    }
  }
  private isValidFiles(files) {
    // Check Number of files
    if (files.length > this.maxFiles) {
      this.errors.push("Error: At a time you can upload only " + this.maxFiles + " files");
      return;
    }
    this.isValidFileExtension(files);
    return this.errors.length === 0;
  }
  private isValidFileExtension(files) {
    // Make array of file extensions
    var extensions = (this.fileExt.split(','))
      .map(function (x) { return x.toLocaleUpperCase().trim() });
    for (var i = 0; i < files.length; i++) {
      // Get file extension
      var ext = files[i].name.toUpperCase().split('.').pop() || files[i].name;
      // Check the extension exists
      var exists = extensions.includes(ext);
      if (!exists) {
        this.errors.push("Invalid file : " + files[i].name+", Upload only "+this.fileExt+" file.");
      }
      // Check file size
      this.isValidFileSize(files[i]);
    }
  }
  private isValidFileSize(file) {
    var fileSizeinMB = file.size / (1024 * 1000);
    var size = Math.round(fileSizeinMB * 100) / 100; // convert upto 2 decimal place
    if (size > this.maxSize)
      this.errors.push("Error (File Size): " + file.name + ": exceed file size limit of " + this.maxSize + "MB ( " + size + "MB )");
  }
  clearFiles() {
    this.errors = [];
    this.successfiles = [];
    this.fileList = [];
    this.uploadStatus.emit(null);
  }
}
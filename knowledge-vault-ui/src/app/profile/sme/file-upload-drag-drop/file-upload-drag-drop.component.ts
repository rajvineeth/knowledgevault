import { FormControl } from '@angular/forms';
import { Component, OnInit, Input, Output, EventEmitter, HostListener } from '@angular/core';

@Component({
  selector: 'app-file-upload-drag-drop',
  templateUrl: './file-upload-drag-drop.component.html',
  styleUrls: ['./file-upload-drag-drop.component.css']
})

export class FileUploadDragDropComponent implements OnInit {

  errors: Array<string> = [];
  successfiles: Array<string> = [];
  dragAreaClass = 'dragarea';
  fileList = [];
  formats = new FormControl();
  formatList: string[] = ['PDF', 'csv', 'doc', 'xls', 'text', 'JSON'];
  formatMessage: string;
  formatCondition: boolean;
  someFile;

  @Input() maxFiles = 10;
  @Input() maxSize = 50;
  @Input() bar = false;
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
    this.formatCondition = true;
  }

  ngOnInit() { 
  }

  @HostListener('click', ['$event'])
  onFileChange(event) {
    
    if(!this.formats.hasError('required')){
      this.formatCondition = true;
      const files = event.target.files;
      this.saveFiles(files);
    }
    
    else { this.formatMessage = 'Please select file format(s)'; 
          this.formatCondition = false;     
  }
  }

  @HostListener('dragover', ['$event']) onDragOver(event) {
    
    if(!this.formats.hasError('required')){
      this.formatCondition = true;
    this.dragAreaClass = 'droparea';
    event.preventDefault(); }
    
    else { this.formatMessage = 'Please select file format(s)'; 
          this.formatCondition = false;  
  }
  }

  @HostListener('dragenter', ['$event']) onDragEnter(event) {
    
    if(!this.formats.hasError('required')){
      this.formatCondition = true;
    this.dragAreaClass = 'droparea';
    event.preventDefault(); }
    
    else { this.formatMessage = 'Please select file format(s)'; 
          this.formatCondition = false;   
  }
  }
  @HostListener('dragleave', ['$event']) onDragLeave(event) {
    
    if(!this.formats.hasError('required')){
      this.formatCondition = true;
    this.dragAreaClass = 'dragarea';
    event.preventDefault(); }
    
    else { this.formatMessage = 'Please select file format(s)'; 
    this.formatCondition = false;   
}
  }

  @HostListener('drop', ['$event']) onDrop(event) {
    
    if(!this.formats.hasError('required')){
      this.formatCondition = true;
    this.dragAreaClass = 'dragarea';
    event.preventDefault();
    event.stopPropagation();
    const files = event.dataTransfer.files; 
    this.saveFiles(files); }
    
    else { this.formatMessage = 'Please select file format(s)'; 
    this.formatCondition = false;   
}
  }

  saveFiles(files) {
    this.errors = [];
    // Validate file size and allowed extensions
    if (files.length > 0 && (!this.isValidFiles(files))) {
      this.uploadStatus.emit(false);
      return;
    }
    if (files.length > 0 && (this.isValidFiles(files))) {
      for (let j = 0; j < files.length; j++) {
        if (!this.fileList.some(x => x.name === files[j].name)) {
          this.fileList.push(files[j]);
          // alert('file added successfully');
          this.successfiles.push(files[j].name + ' added successfully');
        } else {
          this.errors.push('File: ' + files[j].name + ' Already added in list');
        }
      }
      this.uploadStatus.emit(this.fileList);
      return;
    }
  }

  private isValidFiles(files) {
    // Check Number of files
    if (files.length > this.maxFiles) {
      this.errors.push('Error: At a time you can upload only ' + this.maxFiles + ' files');
      return;
    }

    this.isValidFileSize(files);
    return this.errors.length === 0;
  }

  private isValidFileSize(files) {

    for (let i = 0; i < files.length; i++) {

    const fileSizeinMB = files[i].size / (1024 * 1000);
    const size = Math.round(fileSizeinMB * 100) / 100;
    if (size > this.maxSize) {
      this.errors.push('Error (File Size): ' + files[i].name + ': exceed file size limit of ' + this.maxSize + 'MB ( ' + size + 'MB )');
    }
  }
  }

  clearFiles() {
    this.errors = [];
    this.successfiles = [];
    this.fileList = [];
    this.uploadStatus.emit(null);
  }

}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FileUploadComponent } from './file-upload-drag-drop/file-upload-drag-drop.component';
import { DragNDropComponent } from './drag-ndrop/drag-ndrop.component';


@NgModule({
  declarations: [
    DragNDropComponent,
    FileUploadComponent,
    DragNDropComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [DragNDropComponent]
})
export class DragNDropModule { }

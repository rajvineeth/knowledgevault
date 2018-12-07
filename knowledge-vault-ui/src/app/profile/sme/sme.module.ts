import { FooterComponent } from './../../footer/footer.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomMaterialModule } from 'src/app/core/material.module';
import { SmeComponent } from './sme.component';
import { FileUploadDragDropComponent } from './file-upload-drag-drop/file-upload-drag-drop.component';
import { DragndropComponent } from './dragndrop/dragndrop.component';

@NgModule({
  declarations: [SmeComponent, DragndropComponent, FileUploadDragDropComponent],
  imports: [
    CommonModule,
    CustomMaterialModule
  ]
})

export class SmeModule { }

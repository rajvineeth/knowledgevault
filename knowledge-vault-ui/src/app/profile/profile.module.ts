import { CustomMaterialModule } from './../core/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SmeComponent } from './sme/sme.component';
import { DragndropComponent } from './sme/dragndrop/dragndrop.component';
import { FileUploadDragDropComponent } from './sme/file-upload-drag-drop/file-upload-drag-drop.component';
import { HomeComponent } from '../home/home.component';

@NgModule({
  declarations: [SmeComponent,FileUploadDragDropComponent,DragndropComponent],
  imports: [
    CommonModule,
    CustomMaterialModule,
  ]
})

export class ProfileModule { }

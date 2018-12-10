import { CustomMaterialModule } from './../core/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GeneralUserComponent } from './general-user/general-user.component';
import { SmeModule } from './sme/sme.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [GeneralUserComponent],
  imports: [
    CommonModule,
    FormsModule,
    CustomMaterialModule,
    SmeModule
  ]
})

export class ProfileModule { }

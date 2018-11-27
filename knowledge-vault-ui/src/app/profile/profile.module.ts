import { CustomMaterialModule } from './../core/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GeneralUserComponent } from './general-user/general-user.component';
import { SmeModule } from './sme/sme.module';

@NgModule({
  declarations: [GeneralUserComponent],
  imports: [
    CommonModule,
    CustomMaterialModule,
    SmeModule
  ]
})

export class ProfileModule { }

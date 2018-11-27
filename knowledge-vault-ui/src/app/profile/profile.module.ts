import { CustomMaterialModule } from './../core/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GeneralUserComponent } from './general-user/general-user.component';
import { SmeModule } from './sme/sme.module';
import { SearchComponent } from './search/search.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [GeneralUserComponent, SearchComponent],
  imports: [
    CommonModule,
    FormsModule,
    CustomMaterialModule,
    SmeModule
  ]
})

export class ProfileModule { }

import { AppModule } from './../app.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GeneralUserComponent } from './general-user/general-user.component';

@NgModule({
  declarations: [GeneralUserComponent],
  imports: [
    CommonModule
  ]
})
export class ProfileModule { }

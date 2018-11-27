import { CustomMaterialModule } from './core/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UserQueryComponent } from './user-query/user-query.component';
import { ProfileModule } from './profile/profile.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    HomeComponent,
    RegisterComponent,
    HeaderComponent,
    FooterComponent,
    UserQueryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    ProfileModule
  ],
  exports: [
    HomeComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

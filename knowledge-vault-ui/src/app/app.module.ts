import { CustomMaterialModule } from './core/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
<<<<<<< HEAD
import { SpeechModule } from '../lib/';
=======

>>>>>>> 942c824be7dad1fca44f7ce6c89578f874f627c7
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UserQueryComponent } from './user-query/user-query.component';
import { QueryResultsComponent } from './query-results/query-results.component';
import { ProfileModule } from './profile/profile.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    HeaderComponent,
    FooterComponent,
    UserQueryComponent,
    QueryResultsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
<<<<<<< HEAD
    SpeechModule,
    HttpClientModule
  ],
  providers: [
    { provide: 'SPEECH_LANG', useValue: 'en-US' },
=======
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ProfileModule
>>>>>>> 942c824be7dad1fca44f7ce6c89578f874f627c7
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

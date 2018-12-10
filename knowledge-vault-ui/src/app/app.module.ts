import { LoginService } from './login.service';
import { DataService } from './data.service';
import { CustomMaterialModule } from './core/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SpeechModule } from '../lib/';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UserQueryComponent } from './user-query/user-query.component';
import { QueryResultsComponent } from './query-results/query-results.component';
import { ProfileModule } from './profile/profile.module';
import { HttpClientModule } from '@angular/common/http';
import { CardDetailComponent } from './card-detail/card-detail.component';
import { ShareService } from './share.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CardComponent } from './card/card.component';
import { SymptomsComponent } from './card/symptoms/symptoms.component';
import { ExpansionPanelsComponent } from './card/expansion-panels/expansion-panels.component';
import { UserQueryService } from './user-query.service';
import { RegistrationService } from './registration.service';
import { AccordionComponent } from './card/accordion/accordion.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { MongoService } from './mongo.service';
import { CrawlerService } from './crawler.service';
import { SpinnerComponent } from './spinner/spinner.component';
import { SymptomCardComponent } from './card/symptom-card/symptom-card.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    HeaderComponent,
    FooterComponent,
    UserQueryComponent,
    QueryResultsComponent,
    CardDetailComponent,
    CardComponent,
    SymptomsComponent,
    ExpansionPanelsComponent,
    AccordionComponent,
    UserProfileComponent,
    SpinnerComponent,
    SymptomCardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    CustomMaterialModule,
    BrowserAnimationsModule,
    SpeechModule,
    HttpClientModule,
    ProfileModule,
    FlexLayoutModule
  ],
  providers: [
    { provide: 'SPEECH_LANG', useValue: 'en-US' },
    FormsModule,
    ReactiveFormsModule,
    ShareService,
    UserQueryService,
    DataService,
    RegistrationService,
    LoginService,
    MongoService,
    CrawlerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

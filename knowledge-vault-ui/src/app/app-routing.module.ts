
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { QueryResultsComponent } from './query-results/query-results.component';
import { SmeComponent } from './profile/sme/sme.component';
import { CardDetailComponent } from './card-detail/card-detail.component';

const routes: Routes = [
  { path: 'carddetail', component: CardDetailComponent},
  { path: 'login', component: LoginComponent},
  { path: 'sme', component: SmeComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'home', component: HomeComponent},
  { path: 'user', redirectTo: '/home' , pathMatch: 'full'},
  { path: 'queryresults', component: QueryResultsComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

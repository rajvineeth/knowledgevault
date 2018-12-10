
import { LoginService } from './../login.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ShareService } from '../share.service';
import { User } from '../models/auth/user';
import { MongoService } from '../mongo.service';
import { UserDetails } from '../models/reg/userdetails';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  username: string;
  password: string;
  token: string;
  invalidUser: string;

  userDetails: UserDetails;

  logInStatus = true;

  constructor(
    private router: Router, private srvc: ShareService,
    private loginsrvc: LoginService, private mongo: MongoService
  ) { }

  ngOnInit() { }

  shurukaro() {
    this.logIn();
  }

  getUserDetails() {
    console.log('inside getUserDetails: ', this.username);
    this.mongo.fetchUserData(this.username, this.token)
      .subscribe(data => {
        this.userDetails = data;
        localStorage.setItem('userdetail', JSON.stringify(this.userDetails));
        localStorage.setItem('username', this.userDetails.username);
        localStorage.setItem('userrole', this.userDetails.role);
      }
      );
  }

  logIn(): void {
    console.log('getting the flag value before actually logging in');

    const user = new User(this.username, this.password);

    this.loginsrvc.login(user)
      .subscribe(
        data => {
          this.token = data.token;
          localStorage.setItem('usertoken', this.token);
          console.log(this.token);
        }
      );

    this.getUserDetails();
    if (this.userDetails.username === this.username) {
      console.log(this.userDetails.role);
      if (this.userDetails.role === 'General User') {
        this.router.navigate(['home']);
        window.location.reload();
      } else {
        if (this.userDetails.username === 'arpit' && this.userDetails.role === 'Subject Matter Expert') {
          this.router.navigate(['sme/arpit']);
          window.location.reload();
        } else {
          this.invalidUser = ' invalid credentials';
          localStorage.removeItem('username');
          localStorage.removeItem('usertoken');
          localStorage.removeItem('userrole');
          this.router.navigate(['login']);
        }
      }
    }
  }
}

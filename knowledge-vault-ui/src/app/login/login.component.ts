
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

  userDetails: UserDetails;

  logInStatus: boolean;

  constructor(private router: Router, private srvc: ShareService, private loginsrvc: LoginService, private mongo: MongoService) {
    this.srvc.getValue()
      .subscribe(
        val => {
          this.logInStatus = !val;
        }
      );
  }

  ngOnInit() { }

  bhejdo(): void {
    this.srvc.setValue(this.logInStatus);
  }

  shurukaro() {
    // if (this.username === 'abc') {
    //   this.router.navigate(['sme']);
    // } else {
    //   this.router.navigate(['user']);
    // }
    this.login();
  }

  login(): void {
    console.log('getting the flag value before actually logging in');

    const user = new User(this.username, this.password);
    this.mongo.fetchUserData(this.username)
      .subscribe(data => {
        this.userDetails = data;
        console.log('user details', Object.values(this.userDetails));
      });

    this.loginsrvc.validateUser(user)
      .subscribe(
        data => {
          console.log('data from validation ', data);
          if (data.username === this.username) {
            this.bhejdo();
            console.log('role name', this.userDetails.role);
            if (this.userDetails.role === 'General User') {
              this.router.navigate(['user']);
            } else {
              this.router.navigate(['sme']);
            }
            window.location.reload();
          } else {
            alert('Invalid Credentials');
          }
        }
      );
  }
}

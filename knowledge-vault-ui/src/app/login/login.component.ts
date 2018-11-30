
import { LoginService } from './../login.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ShareService } from '../share.service';
import { User } from '../models/auth/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  username: string;
  password: string;

  logInStatus: boolean;

  constructor(private router: Router, private srvc: ShareService, private loginsrvc: LoginService) {
    this.srvc.getValue()
      .subscribe(
        val => {
          this.logInStatus = !val;
        }
      )
  }

  ngOnInit() { }

  bhejdo(): void {
    this.srvc.setValue(this.logInStatus)
  }

  login(): void {
    console.log('getting the flag value before actually logging in')

    const user = new User(this.username, this.password);
    this.loginsrvc.validateUser(user)
      .subscribe(
        data => {
          if( data.username === this.username) {
            this.bhejdo();
            if( data.role === 'General User') {
              this.router.navigate(['sme']);
            }
            else this.router.navigate(['user'])
          }
          else alert('Invalid Credentials');
        }
      )
  }
}
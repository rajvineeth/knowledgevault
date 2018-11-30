import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ShareService } from '../share.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  username: string;
  password: string;

  logInStatus: boolean;

  constructor(private router: Router, private srvc: ShareService) {
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

  login(): void {
    console.log('getting the flag value before actually logging in');

    if (this.username === 'admin' && this.password === 'admin') {
      this.bhejdo();
      this.router.navigate(['user']);
    } else if (this.username === 'sme' && this.password === 'sme') {
      console.log('flag after calling the login() button : ' + this.logInStatus);
      this.bhejdo();
      this.router.navigate(['sme']);
    } else {
      alert('Invalid credentials');
    }
  }
}

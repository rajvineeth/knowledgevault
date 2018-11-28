// import { Router } from '@angular/router';
// import { Component, OnInit } from '@angular/core';
// import { ShareService } from '../share.service';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })

// export class LoginComponent implements OnInit {

//   username: string;
//   password: string;

//   logInStatus: boolean;

//   constructor(private router: Router, private srvc: ShareService) {
//     this.srvc.getValue()
//       .subscribe(
//         val => {
//           this.logInStatus = !val;
//         }
//       );
//   }

//   ngOnInit() { }

//   bhejdo(): void {
//     this.srvc.setValue(this.logInStatus);
//   }

//   login(): void {
//     console.log('getting the flag value before actually logging in');

//     if (this.username === 'admin' && this.password === 'admin') {
//       this.bhejdo();
//       this.router.navigate(['user']);
//     } else if (this.username === 'sme' && this.password === 'sme') {
//       console.log('flag after calling the login() button : ' + this.logInStatus);
//       this.bhejdo();
//       this.router.navigate(['sme']);
//     } else {
//       alert('Invalid credentials');
//     }
//   }
// }



import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService, AuthenticationService } from '../_services';

@Component({ templateUrl: 'login.component.html',
providers: [AlertService, AuthenticationService] })
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    // reset login status
    this.authenticationService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        data => {

          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}

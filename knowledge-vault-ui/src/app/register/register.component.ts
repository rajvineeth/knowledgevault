import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { AlertService, UserService } from '../_services';
import { AlertsService } from 'angular-alert-module';
import { RegistrationService } from '../registration.service';

@Component({
  templateUrl: 'register.component.html',
  providers: [AlertService, AlertsService, UserService],
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;
    details = [];
    msg;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private register: RegistrationService,
        private userService: UserService,
        private alertService: AlertService,
        private alerts: AlertsService) { }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]],
        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit(value) {
        this.submitted = true;

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }
        this.details.push(value);
        console.log(this.details);

        this.loading = true;

        const fn: string = this.registerForm.controls['firstName'].value;
        const ln: string = this.registerForm.controls['lastName'].value;
        const un: string = this.registerForm.controls['username'].value;
        const r = 'General User';
        const pwd: string = this.registerForm.controls['password'].value;

        this.register.registerUser(fn, ln, un, r, pwd)
            .subscribe(
                data => {
                    this.alerts.setMessage('succesfully saved', 'success');
                    this.router.navigate(['/login']);
                }
            );
    }
}

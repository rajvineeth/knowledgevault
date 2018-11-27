import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { User } from '../_models';
import { UserService } from '../_services';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [User, UserService]
})

export class HeaderComponent implements OnInit {
  currentUser: User;
  users: User[] = [];

  constructor(private router: Router, private userService: UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
   }

  ngOnInit() {
    this.loadAllUsers();
  }

  home(): void {
    this.router.navigate(['']);
  }

  login(): void {
    this.router.navigate(['login']);
  }

  register(): void {
    this.router.navigate(['register']);
  }

  private loadAllUsers() {
    this.userService.getAll().pipe(first()).subscribe(users => {
        this.users = users;
    });
}
}

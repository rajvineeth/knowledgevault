import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../_models';
import { UserService } from '../_services';
import { ShareService } from '../share.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [User, UserService]
})

export class HeaderComponent implements OnInit {
  currentUser: User;
  users: User[] = [];
  amILoggedOut: boolean;

  constructor(private router: Router, private userService: UserService, private srvc: ShareService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.srvc.getValue()
      .subscribe(
        data => this.amILoggedOut = data
      )
  }

  ngOnInit() {
    this.amILoggedOut = true;
    this.loadAllUsers();
  }

  home(): void {
    this.router.navigate(['']);
  }

  login(): void {
    console.log('sending the flag from header button...')
    this.srvc.setValue(this.amILoggedOut)
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

  logout(): void {
    this.amILoggedOut = true;
    this.router.navigate(['home'])
  }

  sendMeToQueryPage() {

  }
}

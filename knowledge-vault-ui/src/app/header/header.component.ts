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

  /**
   * private constructor to inject other components and/or services.
   * @param router the router object to help navigate to a different route.
   * @param userService the service typescript class to get list of all Users stored in the database.
   * @param srvc the service typescript class to share data from one component to another
   * component irrespective of their relationship
   */
  constructor(private router: Router, private userService: UserService, private srvc: ShareService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.srvc.getValue()
      .subscribe(
        data => this.amILoggedOut = data
      );
  }

  ngOnInit() {
    this.amILoggedOut = true;
    this.loadAllUsers();
  }

  /**
   * this function provides the routing for home component
   */
  home(): void {
    this.router.navigate(['']);
  }

  /**
   * this function provides the routing for login component
   */
  login(): void {
    console.log('sending the flag from header button...');
    this.srvc.setValue(this.amILoggedOut);
    this.router.navigate(['login']);
  }

  /**
   * this function provides the routing for register component
   */
  register(): void {
    this.router.navigate(['register']);
  }

  /**
   * this function loads all the users stored in the database.
   */
  private loadAllUsers() {
    this.userService.getAll().pipe(first()).subscribe(users => {
      this.users = users;
    });
  }

  /**
   * this function provides the routing for logout component
   */
  logout(): void {
    this.amILoggedOut = true;
    this.router.navigate(['home']);
  }
}

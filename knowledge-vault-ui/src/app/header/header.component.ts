import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../_models';
import { UserService } from '../_services';
import { ShareService } from '../share.service';
import { UserDetails } from '../models/reg/userdetails';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [User, UserService]
})

export class HeaderComponent implements OnInit {
  currentUser: User;
  username = 'User';
  userThere = false;

  /**
   * private constructor to inject other components and/or services.
   * @param router the router object to help navigate to a different route.
   * @param userService the service typescript class to get list of all Users stored in the database.
   * @param srvc the service typescript class to share data from one component to another
   * component irrespective of their relationship
   */

  constructor(private router: Router, private srvc: ShareService) {

    const name = localStorage.getItem('username');
    console.log(name)
    if (name === null || name === undefined) { }
    else {
      console.log('here....')
      this.userThere = true;
      this.username = name;
    }
  }

  ngOnInit() {}

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
    this.router.navigate(['login']);
  }

  /**
   * this function provides the routing for register component
   */
  register(): void {
    // localStorage.removeItem('username');
    // localStorage.removeItem('usertoken');
    // localStorage.removeItem('userrole');
    // this.userThere = false;
    this.router.navigate(['register']);
  }

  /**
   * this function provides the routing for logout component
   */
  logout(): void {
    localStorage.removeItem('username');
    localStorage.removeItem('usertoken');
    localStorage.removeItem('userrole')
    this.userThere = false;
    this.router.navigate(['home']);
  }

  goToProfile() {
    console.log('kaarya pragati pe hai....')
  }
}

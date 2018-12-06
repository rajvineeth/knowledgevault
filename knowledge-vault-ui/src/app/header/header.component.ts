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
  amILoggedOut: boolean;

  /**
   * private constructor to inject other components and/or services.
   * @param router the router object to help navigate to a different route.
   * @param userService the service typescript class to get list of all Users stored in the database.
   * @param srvc the service typescript class to share data from one component to another
   * component irrespective of their relationship
   */
  constructor(private router: Router, private srvc: ShareService) {
    this.srvc.getValue()
      .subscribe(
        data => this.amILoggedOut = data
      );

    const name = localStorage.getItem('userdata');
    console.log(name);
    if (name !== undefined && name != null) {
      this.username = name;
      this.userThere = true;
    }
  }

  ngOnInit() {
    this.amILoggedOut = true;
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
    console.log('sending the flag from header button with value :',this.amILoggedOut);
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
   * this function provides the routing for logout component
   */
  logout(): void {
    this.amILoggedOut = true;
    localStorage.removeItem('currentuser');
    localStorage.removeItem('userdata');
    this.userThere = false;
    this.router.navigate(['home']);
  }

  goToProfile() {
    const userDetail = localStorage.getItem('userdata');
    this.router.navigate(['profile'],{queryParams: {userDetail}});
  }
}

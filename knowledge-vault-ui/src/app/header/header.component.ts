import { Component, OnInit } from '@angular/core';
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
  username = 'User';
  userThere = false;
  isSme = false;

  /**
   * private constructor to inject other components and/or services.
   * @param router the router object to help navigate to a different route.
   * @param userService the service typescript class to get list of all Users stored in the database.
   * @param srvc the service typescript class to share data from one component to another
   * component irrespective of their relationship
   */

  constructor(private router: Router) {

    const name = localStorage.getItem('username');
    console.log(name);
    if (name === null || name === undefined) { } else {
      console.log('here....');
      this.userThere = true;
      this.username = name;
    }

    const role = localStorage.getItem('userrole');
    console.log('role value: ', role);
    if (role === 'Subject Matter Expert') {
      this.isSme = true;
    }

  }

  ngOnInit() { }

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
    this.router.navigate(['register']);
  }

  /**
   * this function provides routing for authorized users to upload documents to add to knowledgebase
   */
  gotoSME(): void {
    const name = localStorage.getItem('username');
    this.router.navigate(['sme/' + name]);
  }

  /**
   * this function provides the routing for logout component
   */
  logout(): void {
    this.isSme = false;
    localStorage.removeItem('username');
    localStorage.removeItem('usertoken');
    localStorage.removeItem('userrole');
    this.userThere = false;
    this.router.navigate(['home']);
  }
}

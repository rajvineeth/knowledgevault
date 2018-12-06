import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../models/reg/userdetails';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  private userDetail: UserDetails;

  constructor() { }

  ngOnInit() {
    this.userDetail = JSON.parse(localStorage.getItem('userdetail'));
    console.log('user details: ', this.userDetail);
  }

}

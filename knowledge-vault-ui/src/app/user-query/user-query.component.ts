import { UserQueryService } from './../user-query.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-query',
  templateUrl: './user-query.component.html',
  styleUrls: ['./user-query.component.css']
})
export class UserQueryComponent implements OnInit {

  inputText: string;

  constructor(private router: Router, private service: UserQueryService) { }

  ngOnInit() {
  }

  search() {
    console.log(this.inputText);
    // this.service.postUserQuery(this.inputText);
    this.router.navigate(['queryresults']);
  }

}

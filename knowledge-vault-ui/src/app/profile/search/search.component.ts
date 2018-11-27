import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  inputText: string;

  constructor(private router: Router, private service: SearchService) { }

  ngOnInit() {
  }

  search() {
    console.log(this.inputText);
    // this.service.postUserQuery(this.inputText);
    this.router.navigate(['queryresults']);
  }
}

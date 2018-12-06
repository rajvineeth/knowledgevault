import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sme',
  templateUrl: './sme.component.html',
  styleUrls: ['./sme.component.css']
})
export class SmeComponent implements OnInit {

  urlPath: string;
  constructor() { }

  ngOnInit() { }

  fetchResource(input: string) {
    console.log(input)
  }

}

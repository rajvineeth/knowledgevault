import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-symptoms',
  templateUrl: './symptoms.component.html',
  styleUrls: ['./symptoms.component.css']
})

export class SymptomsComponent implements OnInit {

  @Input() private symptoms: string[];
  constructor() { }

  ngOnInit() {
  }

}

import { Component, OnInit, Input } from '@angular/core';
import { Paragraph } from 'src/app/models/paragraph';

@Component({
  selector: 'app-expansion-panels',
  templateUrl: './expansion-panels.component.html',
  styleUrls: ['./expansion-panels.component.css']
})
export class ExpansionPanelsComponent implements OnInit {

  @Input() private paragraph: Paragraph;
  constructor() { }

  ngOnInit() {
  }

}

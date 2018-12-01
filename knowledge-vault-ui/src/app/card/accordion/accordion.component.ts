import { Component, OnInit, Input } from '@angular/core';
import { Paragraph } from 'src/app/models/paragraph';

@Component({
  selector: 'app-accordion',
  templateUrl: './accordion.component.html',
  styleUrls: ['./accordion.component.css']
})
export class AccordionComponent implements OnInit {

  @Input() private paragraphs: Paragraph[];
  constructor() { }

  ngOnInit() {
  }

}
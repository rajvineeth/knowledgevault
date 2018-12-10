import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-accordion',
  templateUrl: './accordion.component.html',
  styleUrls: ['./accordion.component.css']
})
export class AccordionComponent implements OnInit {

  @Input() private paragraphs: string[];
  constructor() { }

  ngOnInit() {
  }

  excessPara() {
    if ( this.paragraphs.length > 2) {
      return true;
    }
    return false;
  }
}

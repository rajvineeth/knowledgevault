import { Component, OnInit, Input } from '@angular/core';
import { Paragraph } from 'src/app/models/paragraph';

@Component({
  selector: 'app-expansion-panels',
  templateUrl: './expansion-panels.component.html',
  styleUrls: ['./expansion-panels.component.css']
})
export class ExpansionPanelsComponent implements OnInit {

  @Input() private paragraph: Paragraph;
  private visiblePara: String;
  private hiddenPara: String;
  private valuePara = 'none';
  private valueDots = 'inline';
  constructor() {   }

  ngOnInit() {
    if (this.paragraph.Content.length > 150) {
      this.visiblePara =  this.paragraph.Content.substr(0, 150);
      this.hiddenPara = this.paragraph.Content.substr(150);
    } else {
      this.visiblePara = this.paragraph.Content;
      this.hiddenPara = null;
    }
  }

  public showMore() {

    if (this.valuePara === 'none') {
      this.valuePara = 'inline';
      this.valueDots = 'none';
    } else {
      this.valuePara = 'none';
      this.valueDots = 'inline';
    }
  }

}

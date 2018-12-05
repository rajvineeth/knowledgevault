import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-expansion-panels',
  templateUrl: './expansion-panels.component.html',
  styleUrls: ['./expansion-panels.component.css']
})
export class ExpansionPanelsComponent implements OnInit {

  @Input() private paragraph: string;
  private visiblePara: string;
  private hiddenPara: string;
  private valuePara = 'none';
  private valueDots = 'inline';
  constructor() {   }

  ngOnInit() {
    if (this.paragraph.length > 150) {
      this.visiblePara =  this.paragraph.substr(0, 150);
      this.hiddenPara = this.paragraph.substr(150);
    } else {
      this.visiblePara = this.paragraph;
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

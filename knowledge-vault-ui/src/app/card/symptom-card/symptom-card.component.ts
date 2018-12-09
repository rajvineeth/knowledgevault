import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-symptom-card',
  templateUrl: './symptom-card.component.html',
  styleUrls: ['./symptom-card.component.css']
})
export class SymptomCardComponent implements OnInit {

  @Input() private symptoms: string[];
  @Input() private subHeader: string;
  private slicedSymptoms: string[];
  private more = false;
  private valueLess = 'none';
  private valueDots = 'inline';
  constructor() { }

  ngOnInit() {
    this.symptoms = this.symptoms.filter(item => item !== 'NULL');
    for (let i = 0; i < this.symptoms.length; i++) {
      this.symptoms[i] = this.symptoms[i].substr(1, this.symptoms[i].length - 2);
      this.symptoms[i] = this.symptoms[i].charAt(0).toUpperCase() + this.symptoms[i].substr(1);
    }
    this.slicedSymptoms = this.symptoms.slice(0, 3);
    if (this.symptoms.length > 3) {
      this.more = true;
    }
  }

  public showMore() {

    if (this.valueLess === 'none') {
      this.valueLess = 'inline';
      this.valueDots = 'none';
    } else {
      this.valueLess = 'none';
      this.valueDots = 'inline';
    }
  }

}

import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-symptom-card',
  templateUrl: './symptom-card.component.html',
  styleUrls: ['./symptom-card.component.css']
})
export class SymptomCardComponent implements OnInit {

  @Input() private symptoms: string[];
  private slicedSymptoms: string[];
  constructor() { }

  ngOnInit() {
    this.symptoms.forEach(element => {
      element = this.ucFirst(element);
    });
    this.slicedSymptoms = this.symptoms.slice(0, 3);
  }

  ucFirst(name: string) {
    return name.charAt(0).toUpperCase() + name.substr(1);
  }

}

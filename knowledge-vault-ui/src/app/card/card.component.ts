import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ReceivedQuery } from '../models/receivedQuery';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() private card: ReceivedQuery;
  private name: string;
  private alternateName: string;

  constructor(private router: Router) {
   }

  ngOnInit() {
    this.name = this.card.diseaseName.substr(1, this.card.diseaseName.length - 2);
    const partsOfName = this.name.trim().split('\u000b');
    if (partsOfName.length > 1) {
      this.name = partsOfName[0];
      this.alternateName = partsOfName[1];
      this.alternateName = this.ucFirst(this.alternateName);
    }
    this.name = this.ucFirst(this.name);
   }

  detailedView(card: ReceivedQuery) {
    this.router.navigate(['carddetail/{{card.MedicalCondition}}']);
  }

  ucFirst(name: string) {
    return name.charAt(0).toUpperCase() + name.substr(1);
  }
}

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
  private paragraphs: string[];

  constructor(private router: Router) {
   }

  ngOnInit() {
    this.paragraphs = this.paragraphs.concat(this.card.para);
    this.paragraphs = this.paragraphs.concat(this.card.doc);
  }

  detailedView(card: ReceivedQuery) {
    this.router.navigate(['carddetail/{{card.MedicalCondition}}']);
  }
}

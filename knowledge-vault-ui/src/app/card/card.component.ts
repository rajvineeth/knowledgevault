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

  constructor(private router: Router) {
   }

  ngOnInit() {  }

  detailedView(card: ReceivedQuery) {
    this.router.navigate(['carddetail/{{card.MedicalCondition}}']);
  }
}

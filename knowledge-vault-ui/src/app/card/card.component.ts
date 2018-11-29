import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ReceivedQuery } from '../receivedQuery';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  @Input() public res: ReceivedQuery;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  detailedView() {
    this.router.navigate(['carddetail']);
  }
}

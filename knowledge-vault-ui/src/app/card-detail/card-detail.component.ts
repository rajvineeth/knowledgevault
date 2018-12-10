import { Component, OnInit, Input } from '@angular/core';
import { ReceivedQuery } from '../models/receivedQuery';
import { ActivatedRoute } from '@angular/router';
import { UserQueryService } from '../user-query.service';



@Component({
  selector: 'app-card-detail',
  templateUrl: './card-detail.component.html',
  styleUrls: ['./card-detail.component.css']
})
export class CardDetailComponent implements OnInit {

  private card: ReceivedQuery;

  constructor(private route: ActivatedRoute, private userQuery: UserQueryService) { }

  ngOnInit() {
  }

}

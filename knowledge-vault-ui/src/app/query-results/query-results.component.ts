import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { ReceivedQuery } from '../models/receivedQuery';

@Component({
  selector: 'app-query-results',
  templateUrl: './query-results.component.html',
  styleUrls: ['./query-results.component.css']
})
export class QueryResultsComponent implements OnInit {

  public queryResults: ReceivedQuery[];

  constructor(public _dataservice: DataService) { }

  ngOnInit() {
    this._dataservice.getQuery()
        .subscribe(
          data => this.queryResults = data
        );
  }

}

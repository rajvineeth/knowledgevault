import { Component, OnInit, OnChanges } from '@angular/core';
import { DataService } from '../data.service';
import { ReceivedQuery } from '../models/receivedQuery';

@Component({
  selector: 'app-query-results',
  templateUrl: './query-results.component.html',
  styleUrls: ['./query-results.component.css']
})

export class QueryResultsComponent implements OnInit {
  public queryResults: Array<ReceivedQuery>;
  processingDone = false;

  constructor(public _dataservice: DataService) {
    // this.queryResults = null;
    this._dataservice.getQuery()
      .subscribe(
        data => {
          this.queryResults = data;
          if (this.queryResults.length > 0) {
            this.processingDone = true;
            console.log('current data: ', this.queryResults);
          }
        }
      );
  }

  ngOnInit() {
  }

}

import { Component, OnInit, OnChanges, SimpleChange, SimpleChanges } from '@angular/core';
import { DataService } from '../data.service';
import { ReceivedQuery } from '../models/receivedQuery';

@Component({
  selector: 'app-query-results',
  templateUrl: './query-results.component.html',
  styleUrls: ['./query-results.component.css']
})

export class QueryResultsComponent implements OnInit {
  public queryResults: Array<ReceivedQuery>;
  show: boolean;

  constructor(private _dataservice: DataService) {
    this.show = false;
  }

  ngOnInit() {  }

  dikhao(flag: boolean) {
    this._dataservice.getQuery()
      .subscribe(
        data => {
          this.queryResults = data;
          this.show = flag;
        }
      );
  }

}

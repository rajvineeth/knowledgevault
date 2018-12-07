import { Component, OnInit, OnChanges } from '@angular/core';
import { DataService } from '../data.service';
import { ReceivedQuery } from '../models/receivedQuery';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-query-results',
  templateUrl: './query-results.component.html',
  styleUrls: ['./query-results.component.css']
})
export class QueryResultsComponent implements OnInit {

  public queryResults: Array<ReceivedQuery>;
  processingDone = false;

  constructor(public _dataservice: DataService) {

  }

  ngOnInit() {
    this.queryResults = null;
    this._dataservice.getQuery()
      .subscribe(
        data => {
          console.log('got the data: ', Object.values(data));
          this.queryResults = data;
          // if (this.queryResults != null && this.queryResults !== undefined) {
          //   this.processingDone = true;
          // }
          // while (this.queryResults === null || this.queryResults === undefined) {
          //   console.log('waiting for results...');
          // }
          
          this.processingDone = true;
        }
      );
    console.log('current data: ', this.queryResults);

    const inactivity = setTimeout(function () {
      this.processingDone = true;
    }, 3000);
    clearTimeout(inactivity);
    console.log(this.queryResults);
  }

}

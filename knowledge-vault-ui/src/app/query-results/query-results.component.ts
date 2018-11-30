import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { ReceivedQuery } from '../models/receivedQuery';
import { Dummy1 } from '../models/dummy1';

@Component({
  selector: 'app-query-results',
  templateUrl: './query-results.component.html',
  styleUrls: ['./query-results.component.css']
})
export class QueryResultsComponent implements OnInit {

  private dummy: String = 'Hello kity';
  // private dummy1: { MedicalCondition: String; MedicalSymptoms: String[] }
  //                 = { MedicalCondition: 'Cancer', MedicalSymptoms: new String[2](X) };
  private symptoms = Array.of<String>('pain', 'death', 'suffering');
  private dummy1: Dummy1 = { MedicalCondition: 'Cancer', MedicalSymptoms: this.symptoms };
  public queryResults: ReceivedQuery[];

  constructor(public _dataservice: DataService) { }

  ngOnInit() {
    this._dataservice.getQuery()
        .subscribe(
          data => this.queryResults = data
        );
  }

}

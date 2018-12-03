import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { ReceivedQuery } from '../models/receivedQuery';
import { Paragraph } from '../models/paragraph';
import { ReceivedQuery2 } from '../models/received-query2';

@Component({
  selector: 'app-query-results',
  templateUrl: './query-results.component.html',
  styleUrls: ['./query-results.component.css']
})
export class QueryResultsComponent implements OnInit {
  private queryRes: any;
  private symptoms = Array.of<String>('pain', 'death', 'suffering');
  private bodyParts = Array.of<String>('blood', 'lungs', 'mouth');
  private paragraph1: Paragraph = {
    DocumentId: 1,
    Content: 'This is a dummy paragraph to tell you that if you have cancer then there is no way saving you.'
  };
  private paragraph2: Paragraph = {
    DocumentId: 2,
    Content: 'This is to conform that your diaganosis confirms you have cancer. See your future in para 1.'
  };
  private paragraphs = Array.of(
    this.paragraph1,
    this.paragraph2
  );
  private cancer: String = 'Blood Cancer';
  private dummy1 = new ReceivedQuery(this.cancer, this.symptoms, this.bodyParts, this.paragraphs);
  public queryResults: ReceivedQuery[] = Array.of<ReceivedQuery>(this.dummy1);

  constructor(public _dataservice: DataService) { }

  ngOnInit() {
    this._dataservice.getQuery()
        .subscribe(
          // data => this.queryResults = data
          data => this.queryRes = data
        );
  }

}

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
  private recvdMsg;
  private nameOfDisease = 'Chickengunia';
  private nameOfSymptoms = Array.of<String>('pain', 'death', 'suffering');
  private nameOfBodyParts = Array.of<String>('blood', 'lungs', 'mouth');
  private contentOfPara = Array.of<String>(
    'I am paragraph. I want ro be fit  so that i dont cause this card to'
     + ' be very big. i want to hide my own contents so thatit doesnt',
     'This is to conform that your diaganosis confirms you have cancer. See your future in para 1'
  );
  private contentOfDoc = Array.of<String>(
    'I am paragraph of Doc. I want ro be fit  so that i dont cause this card to'
     + ' be very big. i want to hide my own contents so thatit doesnt',
     'This is to conform  of Doc Doc that your diaganosis confirms you have cancer. See your future in para 1'
  );
  private dummy1 = new ReceivedQuery(this.nameOfDisease, this.nameOfBodyParts, this.nameOfSymptoms, this.contentOfPara, this.contentOfDoc);
  public queryResults: Array<ReceivedQuery>;

  constructor(public _dataservice: DataService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this._dataservice.getQuery()
        .subscribe(
          data => {
            // if (this.queryResults === undefined) {}
            console.log('got the data: ', Object.values(data));
            this.queryResults = data;
          }
        );
        console.log(this.queryResults);
  }

}
// .pipe(catchError(err => {
//   console.log('we have a problem here');
//   return throwError(err.error);
// }

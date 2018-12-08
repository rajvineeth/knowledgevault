import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/internal/Subject';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';

@Injectable()
export class ShareService {

  private valueObs: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  public setValue(value: any): void {
    this.valueObs.next(value);
    console.log('value after setValue() is called: ', this.valueObs.value);
  }

  public getValue(): Observable<any> {
    return this.valueObs;
  }
}

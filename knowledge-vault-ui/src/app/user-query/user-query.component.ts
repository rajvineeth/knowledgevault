import { SpeechService } from './../../lib/speech.service';
import { UserQueryService } from './../user-query.service';
import { Component, OnInit , OnDestroy } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-query',
  templateUrl: './user-query.component.html',
  styleUrls: ['./user-query.component.css']
})
export class UserQueryComponent implements OnInit , OnDestroy {

  inputText: string;
  msg = '';
  context = '';
  subscription = Subscription.EMPTY;
  good: any;

  started = false;

  private _destroyed = new Subject<void>();

    constructor(private router: Router, private service: UserQueryService , public speech: SpeechService) { }

  ngOnInit(): void {
        this.speech.start();
        this.speech.message.pipe(
            takeUntil(this._destroyed)
        ).subscribe(msg => this.msg = msg.message);
        this.speech.context.pipe(
            takeUntil(this._destroyed)
        ).subscribe(context =>  this.context = context);
        this.good = {message: 'Try me!'};
        this.speech.started.pipe(
            takeUntil(this._destroyed)
        ).subscribe(started => this.started = started);
    }

    ngOnDestroy(): void {
        this._destroyed.next();
        this._destroyed.complete();
        this.subscription.unsubscribe();
    }

    search() {
        console.log(this.inputText);
        // this.service.postUserQuery(this.inputText);
        this.router.navigate(['queryresults']);
    }

    toggleVoiceRecognition(): void {
        if (this.started) {
            this.speech.stop();
        } else {
            this.speech.start();
        }
    }
<<<<<<< HEAD
=======
=======
  constructor(private router: Router, private service: UserQueryService) { }
>>>>>>> 942c824be7dad1fca44f7ce6c89578f874f627c7


  search() {
    console.log(this.inputText);
    this.service.postUserQuery(this.inputText);
    this.router.navigate(['queryresults']);
  }
>>>>>>> 3f2c308e980ff578e8e5798cc8574e10c459986e

}

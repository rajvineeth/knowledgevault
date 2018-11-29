import { SpeechService } from './../../lib/speech.service';
import { UserQueryService } from './../user-query.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
    selector: 'app-user-query',
    templateUrl: './user-query.component.html',
    styleUrls: ['./user-query.component.css']
})

export class UserQueryComponent implements OnInit, OnDestroy {

    msg = '';
    context = '';
    subscription = Subscription.EMPTY;
    good: any;

    started = false;

    private _destroyed = new Subject<void>();

    constructor(private router: Router, private service: UserQueryService, public speech: SpeechService) { }

    ngOnInit(): void {
        this.speech.message.pipe(
            takeUntil(this._destroyed)
        ).subscribe(msg => this.msg = msg.message);
        this.speech.context.pipe(
            takeUntil(this._destroyed)
        ).subscribe(context => this.context = context);
        this.good = { message: 'Try me!' };
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
        console.log(this.msg);
        this.service.postUserQuery(this.msg);
        this.speech.stop();
        this.router.navigate(['queryresults']);
    }

    toggleVoiceRecognition(): void {
<<<<<<< HEAD
        if (this.started) {
            this.speech.stop();
        }else {
            this.speech.start();
        }
=======
        this.started ? this.speech.stop() : this.speech.start()
>>>>>>> da68ac5949f0c56c7eb3ddb38c68f4f20a250df4
    }

}

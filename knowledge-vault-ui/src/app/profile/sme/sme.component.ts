import { Component, OnInit } from '@angular/core';
import { CrawlerService } from 'src/app/crawler.service';

@Component({
  selector: 'app-sme',
  templateUrl: './sme.component.html',
  styleUrls: ['./sme.component.css']
})
export class SmeComponent implements OnInit {

  urlPath: string;
  constructor(private crawler: CrawlerService) { }

  ngOnInit() { }

  sendUrl(input: string) {
    console.log(input)
    console.log(localStorage.getItem('tokenVal');
    this.crawler.scrapeUrl(input,localStorage.getItem('tokenVal')));
  }

}

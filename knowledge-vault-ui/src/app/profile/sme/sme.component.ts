import { Component, OnInit } from '@angular/core';
import { CrawlerService } from 'src/app/crawler.service';
import { ActivatedRoute } from '@angular/router';
import { UrlClass } from 'src/app/models/urlClass';

@Component({
  selector: 'app-sme',
  templateUrl: './sme.component.html',
  styleUrls: ['./sme.component.css']
})
export class SmeComponent implements OnInit {

  urlPath: string;
  token: string;

  constructor(private crawler: CrawlerService, private route: ActivatedRoute) {}

  ngOnInit() {}

  sendUrl(input: string) {
    console.log(input);
    const url = new UrlClass(input);
    this.crawler.scrapeUrl(url);
  }

}

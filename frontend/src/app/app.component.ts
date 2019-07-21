import {Component, OnInit, AfterViewInit, ViewChild, ElementRef} from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

 public query: string;

  public constructor() {

  }

  ngOnInit(): void {
  }

}

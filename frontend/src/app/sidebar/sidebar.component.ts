import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public onenRout = false;
  openTime = false;
  openPay = false;
  openMessage = false;
  openBuaChar = false;
  openPersonal = false;

  constructor() {
  }

  ngOnInit() {
  }

}

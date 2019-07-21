import {Component, OnInit, ViewChild, ElementRef, AfterViewInit} from '@angular/core';

declare var H: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit {

  private platform: any;

  @ViewChild('map')
  public mapElement: ElementRef;

  public constructor() {
    this.platform = new H.service.Platform({
      app_id: 'uDMPmRz4dztwUWX5l3Qh',
      app_code: 'g8Zw1ehFh_9MorsZ1UgswQ'
    });
  }

  public ngOnInit() {
    const defaultLayers = this.platform.createDefaultLayers();
    const map = new H.Map(
      this.mapElement.nativeElement,
      defaultLayers.normal.map,
      {
        zoom: 13,
        center: {lat: 56.2965039, lng: 43.9360589}
      }
    );

    const mapEvents = new H.mapevents.MapEvents(map);
    map.addEventListener('tap', (evt) => {
      console.log(evt.type, evt.currentPointer.type);
    });

    const behavior = new H.mapevents.Behavior(mapEvents);
  }


  public ngAfterViewInit() {

  }

  onClick(event: any) {
      console.log(event.type);
  }

}

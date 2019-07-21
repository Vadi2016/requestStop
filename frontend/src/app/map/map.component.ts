import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';

declare var H: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit, AfterViewInit {

  private platform: any;

  @ViewChild('map')
  public mapElement: ElementRef;

  @Input()
  public appId: any;

  @Input()
  public appCode: any;

  @Input()
  public lat: any;

  @Input()
  public lng: any;

  @Input()
  public width: any;

  @Input()
  public height: any;

  private map: any;
  private behavior: any;
  private ui: any;
  private search: any;
  DraggableMarkerIsActive = false;

  constructor() {
  }

  public ngOnInit() {
    this.platform = new H.service.Platform({
      app_id: 'uDMPmRz4dztwUWX5l3Qh',
      app_code: 'g8Zw1ehFh_9MorsZ1UgswQ'
    });

    this.search = new H.places.Search(this.platform.getPlacesService());
  }


  ngAfterViewInit(): void {
    const defaultLayers = this.platform.createDefaultLayers();
    this.map = new H.Map(
      this.mapElement.nativeElement,
      defaultLayers.satellite.map,
      {
        zoom: 10,
        center: {lat: this.lat, lng: this.lng}
      }
    );

    this.behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(this.map));

    this.ui = H.ui.UI.createDefault(this.map, defaultLayers, 'ru-RU');
  }


  onClick(event: any) {
    this.map.addEventListener('tap', (evt) => {
      console.log(evt, evt.currentPointer.type);
    });
  }

  public places(query: string) {
    this.map.removeObjects(this.map.getObjects());
    this.search.request({q: query, at: this.lat + ',' + this.lng}, {}, data => {
      for (let i = 0; i < data.results.items.length; i++) {
        this.dropMarker({
          lat: data.results.items[i].position[0],
          lng: data.results.items[i].position[1]
        }, data.results.items[i]);
      }
    }, error => {
      console.error(error);
    });
  }

  private dropMarker(coordinates: any, data: any) {
    const marker = new H.map.Marker(coordinates);
    marker.setData('<p>' + data.title + '<br>' + data.vicinity + '</p>');
    marker.addEventListener('tap', event => {
      const bubble = new H.ui.InfoBubble(event.target.getPosition(), {
        content: event.target.getData()
      });
      this.ui.addBubble(bubble);
    }, false);
    this.map.addObject(marker);
  }

  activateDraggableMarker() {
    if (!this.DraggableMarkerIsActive) {
      this.DraggableMarkerIsActive = true;
      console.log(this.DraggableMarkerIsActive);
    } else {
      this.DraggableMarkerIsActive = false;
      console.log(this.DraggableMarkerIsActive);
    }
  }

  addDraggableMarker() {
    if (this.DraggableMarkerIsActive) {
      this.map.addEventListener('tap', (evt) => {
        const coord = this.map.screenToGeo(evt.currentPointer.viewportX,
          evt.currentPointer.viewportY);
        console.log('Clicked at ' + Math.abs(coord.lat) +
          ' ' + Math.abs(coord.lng));
        const marker = new H.map.Marker({lat: Math.abs(coord.lat), lng: Math.abs(coord.lng)}, {
          // mark the object as volatile for the smooth dragging
          volatility: true
        });
        marker.draggable = true;
        this.map.addObject(marker);
      });
    } else {
      this.map.removeEventListener('tap', (evt) => {
        const coord = this.map.screenToGeo(evt.currentPointer.viewportX,
          evt.currentPointer.viewportY);
        console.log('Clicked at ' + Math.abs(coord.lat) +
          ' ' + Math.abs(coord.lng));
        const marker = new H.map.Marker({lat: Math.abs(coord.lat), lng: Math.abs(coord.lng)}, {
          // mark the object as volatile for the smooth dragging
          volatility: true
        });
        marker.draggable = true;
        this.map.addObject(marker);
      });
    }


    this.map.addEventListener('dragstart', (ev) => {
      const target = ev.target;
      if (target instanceof H.map.Marker) {
        this.behavior.disable();
      }
    }, false);


    // re-enable the default draggability of the underlying map
    // when dragging has completed
    this.map.addEventListener('dragend', (ev) => {
      const target = ev.target;
      if (target instanceof H.map.Marker) {
        this.behavior.enable();
      }
    }, false);

    // Listen to the drag event and move the position of the marker
    // as necessary
    this.map.addEventListener('drag', (ev) => {
      const target = ev.target,
        pointer = ev.currentPointer;
      if (target instanceof H.map.Marker) {
        target.setGeometry(this.map.screenToGeo(pointer.viewportX, pointer.viewportY));
      }
    }, false);

  }


}

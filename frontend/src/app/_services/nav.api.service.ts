import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {RouteTransportModel} from '../_models';

@Injectable({providedIn: 'root'})
export class NavApiService {

  constructor(private http: HttpClient) {

  }


  getRoute(firstLatitude = '1', firstLongitude = '1', lastLatitude = '1', lastLongitude = '1'): Observable<RouteTransportModel> {


    const requestOptions = {
      params: new HttpParams().set('firstLatitude', firstLatitude)
        .set('firstLongitude', firstLongitude)
        .set('lastLatitude', lastLatitude)
        .set('lastLongitude', lastLongitude)
    };
    return this.http.get<RouteTransportModel>(`http://localhost:8080/trans/cxf/route/find`,
      requestOptions
      )
      .pipe(map(route => {
        catchError(this.handleError);
        return route;
      }));
  }

  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

}

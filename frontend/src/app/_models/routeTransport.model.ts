import {PointModel} from './point.model';

export class RouteTransportModel {
  constructor(
    private mnemocode: string,
    private first: string,
    private last: string,
    private timer: string,
    private points: PointModel[]
  ) {
  }
}

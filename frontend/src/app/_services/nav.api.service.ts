import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {API_URL} from '../../environments/environment';
import {map} from 'rxjs/operators';
import {CommentModel} from '../_models';

@Injectable({providedIn: 'root'})
export class NavApiSevice {
  public currentCommentListSubj: BehaviorSubject<CommentModel[]>;
  public currentCommentList: Observable<CommentModel[]>;


  constructor(private http: HttpClient) {
    this.currentCommentListSubj = new BehaviorSubject<CommentModel[]>([]);
    this.currentCommentList = this.currentCommentListSubj.asObservable();
  }

  getAllCommnets(id): Observable<CommentModel[]> {
    return this.http.get<CommentModel[]>(`${API_URL}/tracks/${id}/comments`).pipe(map(comments => {
      if (comments) {
        this.currentCommentListSubj.next(comments);
      }
      return comments;
    }));
  }

  addComment(id, content, userId): Observable<any> {
    return this.http.post<any>(`${API_URL}/tracks/${id}/comments`, new HttpParams().set('content', content).set('userId', userId));
  }

}

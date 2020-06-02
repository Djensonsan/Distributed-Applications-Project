import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  public stateChange: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private url = 'http://localhost:8080/DA_Project/customer/add';

  constructor(private http: HttpClient) {
  }

  send(body) {
    let headers = new HttpHeaders();
    headers = headers.set('content-type', 'application/json');
    return this.http.post(this.url, body, {headers});
  }
}

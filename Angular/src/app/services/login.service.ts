import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public stateChange: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private url = 'http://localhost:8080/DA_Project/ShoppingCartServlet';

  constructor(private http: HttpClient) {
  }

  send(data) {
    const params = new HttpParams().set('email', data.email).set('password', data.password);
    console.log(params);
    return this.http.get(this.url, {params});
  }
}

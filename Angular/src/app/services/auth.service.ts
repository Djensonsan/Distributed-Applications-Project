import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

interface MyData {
  success: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = false;

  constructor(private http: HttpClient) {
  }

  setLoggedIn(arg0: boolean) {
    this.isLoggedIn = arg0;
    throw new Error('Method not implemented.');
  }

  getLoggedIn() {
    return this.isLoggedIn;
  }

  getUserDetails(username, password) {
    // TODO: Get details from API
    return this.http.post<MyData>('http://localhost:8080/DA_Project/auth?email=' + username + '&password=' + password, {});
  }
}

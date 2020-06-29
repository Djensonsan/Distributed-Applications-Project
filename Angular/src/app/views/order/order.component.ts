import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders: any[];

  constructor(private http: HttpClient, private auth: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/DA_Project/customers/getAll')
      .subscribe(response => {
        this.orders = response;
        console.log(response);
        console.log(response[0]);
      });
  }

  logout() {
    this.auth.setLoggedIn(false);
    this.router.navigate(['login']);
  }
}

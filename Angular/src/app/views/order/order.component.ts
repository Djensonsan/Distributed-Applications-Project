import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders: any[];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/DA_Project/order/getAll')
      .subscribe(response => {
        this.orders = response;
        console.log(response);
        console.log(response[0]);
      });
  }
}

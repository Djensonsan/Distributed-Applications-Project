import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-shop-management',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  items: any[];
  shoppingcart: any[];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/DA_Project/items/getAllItems')
      .subscribe(response => {
        this.items = response;
      });

    this.http.get<any>('http://localhost:8080/DA_Project/ShoppingCartServlet')
      .subscribe(response => {
        this.shoppingcart = response;
      });
  }

  add(id) {
    this.http.get<any>('http://localhost:8080/DA_Project/ShoppingCartServlet?product=' + id)
      .subscribe(response => {
        this.shoppingcart = response;
      });
  }

  delete() {
    console.log('sending delete');
    this.http.get<any>('http://localhost:8080/DA_Project/ShoppingCartServlet?delete=true')
      .subscribe(response => {
        this.shoppingcart = response;
      });
  }

  checkout(id) {
    console.log(this.shoppingcart[0].total > 0);
    if (this.shoppingcart[0].total > 0 && id) {
      console.log('sending checkout' + id);
      this.http.get<any>('http://localhost:8080/DA_Project/ShoppingCartServlet?checkout=' + id)
        .subscribe(response => {
          console.log(response);
          this.shoppingcart = response;
        });
      alert('Order Submitted!');
    } else if (id) {
      alert('Please put items in your shoppingcart before Checkout.');
    } else {
      alert('Please enter a Customer ID.');
    }
  }

}

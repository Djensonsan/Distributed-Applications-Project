import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-shop-management',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  items: any[];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/DA_Project/items/getAllItems')
      .subscribe(response => {
        this.items = response;
        console.log(response);
        console.log(response[0].description);
        console.log(response[0].urls[0]);
      });
  }

}

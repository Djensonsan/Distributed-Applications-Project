import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  title = 'ListComponent';
  posts: any[];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<any>('http://localhost:8080/DA_Project/customers/getAll')
      .subscribe(response => {
        this.posts = response;
        console.log(response);
        console.log(response[0].person.firstname);
      });
  }

}

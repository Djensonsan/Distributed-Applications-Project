import {Component, OnInit} from '@angular/core';
import {NgxSoapService} from 'ngx-soap';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private client;

  constructor(private http: HttpClient, private soap: NgxSoapService) {
    this.soap.createClient('assets/quote.wsdl')
      .then(client => {
        console.log('Client', client);
        this.client = client;
        this.getQuote();
      })
      .catch(err => console.log('Error', err));
  }

  getQuote() {
    this.client.call('getSpreuk',null).subscribe(res => {
      console.log(res);
    }, err => console.log(err));
  }

  ngOnInit(): void {
  }

}

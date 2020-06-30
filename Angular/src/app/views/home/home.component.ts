import {Component, OnInit} from '@angular/core';
import {PostService} from '../../services/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private client;
  public quote;

  constructor(private service: PostService) {
  }

  getQuote() {
    this.service.requestSOAP()
      .subscribe(
        response => {
          const wrapper = document.createElement('div');
          wrapper.innerHTML = response;
          const element = wrapper.getElementsByTagName('return')[0];
          this.quote = element.innerHTML;
        }, error => {
          console.log(error);
        });
  }

  ngOnInit(): void {
    this.getQuote();
  }
}

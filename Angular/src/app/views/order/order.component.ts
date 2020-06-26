import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(private service: LoginService) {
  }

  ngOnInit(): void {
  }

  OnLogin(userForm: NgForm) {
    console.log('OnLogin');
    const result = userForm.value;
    console.log(result);
    const body = JSON.stringify(result);
    this.service.send(body)
      .subscribe(response => console.log(response), error => {
        console.log(error);
      });
  }
}

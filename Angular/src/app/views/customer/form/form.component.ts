import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {Customer} from '../../../interfaces/customer';
import {PostService} from '../../../services/post.service';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  customer: Customer;

  constructor(private service: PostService) {
  }

  ngOnInit(): void {

  }

  OnSave(userForm: NgForm) {
    const result = userForm.value;
    console.log(result);
    this.customer = result;
    const body = JSON.stringify(this.customer);
    console.log(body);
    this.service.send(body)
      .subscribe(response => console.log(response), error => {
        console.log(error);
      });
  }
}

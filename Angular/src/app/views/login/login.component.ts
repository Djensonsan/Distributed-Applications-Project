import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  OnLogin(userForm: NgForm) {
    const username = userForm.value.email;
    const password = userForm.value.password;

    console.log('OnLogin' + username + password);
    this.auth.getUserDetails(username, password).subscribe(data => {
      console.log(data);
      if (data.success) {
        this.router.navigate(['order']);
      } else {
        alert('Wrong Credentials');
      }
    });
  }

}

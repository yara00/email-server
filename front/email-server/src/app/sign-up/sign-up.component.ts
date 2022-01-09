import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})

@Injectable()
export class SignUpComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  username = "";
  password = "";
  email = "";
  firstName = "";
  lastName = "";
  birthday = "";
  validation = "true";
  pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";

  send() {
        this.http.get('http://localhost:8080/sign/signup', {
      responseType: 'text',
        params: {
          password: this.password,
          userName: this.email
        },
      observe:'response'
    }).subscribe(response => {
      this.validation = response.body
    })
  }

  signup() {
    if (this.firstName === "") {
      document.getElementById('error').innerText = "Enter a valid First Name";
    }
    else if (this.lastName === "") {
      document.getElementById('error').innerText = "Enter a valid Last Name";
    }
    else if (this.birthday === "") {
      document.getElementById('error').innerText = "Enter a valid Birthday";
    }
    else if (this.username === "") {
      document.getElementById('error').innerText = "Enter a valid Username";
    }
    else if (this.email === "" || this.email.search(this.pattern) === -1) {
      document.getElementById('error').innerText = "Enter a valid Email";
    }
    else if (this.password === "") {
      document.getElementById('error').innerText = "Enter a Password";
    }
    else {
      this.send();
      document.getElementById("sub").onclick = function (event) {
        event.preventDefault();
      };
      if (this.validation === 'true') {
        this.router.navigateByUrl('/main');
      }
      else {
        document.getElementById('error').innerText = 'You already have an account';
      }
    }
  }
}

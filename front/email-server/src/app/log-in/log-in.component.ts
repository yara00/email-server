import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { MainComponent } from '../main/main.component';
import { FileService } from '../file.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
@Injectable()
export class LogInComponent implements OnInit {
  username : string;
  password = "";
  validation = "";
  user = "";

  constructor(private http: HttpClient, private router: Router, private fileServe: FileService) { }
  getUser():string {
    return this.username
  }
  
  setUser():void {
  //  this.mainapp.username = this.username
  }
  ngOnInit(): void {
  }
  check() {
    this.http.get('http://localhost:8080/sign/signin', {
      responseType: 'text',
      params:{
        userName: this.username,
        password: this.password
      },
      observe:'response'
    }).subscribe(response => {
      this.validation = response.body
      console.log(this.validation)
      document.getElementById("sub").onclick = function (event) {
        event.preventDefault();
      };
      if (this.validation === 'true') {
        this.router.navigateByUrl('/main');
        var main =  new MainComponent(this.http, null, null);
        console.log(this.username, "lolo")
      }
      else {
        document.getElementById('error').innerText = 'Invalid Username or Password';
      }
    })
  }

  login() {
    console.log(this.username, 'hena')
    this.fileServe.changeMessage(this.username);

    if (this.username === "") {
      document.getElementById('error').innerText = 'Enter a Username';
    }
    else if (this.password === "") {
      document.getElementById('error').innerText = 'Enter a Password';
    }
    else {
      this.check();
    }
  }
}

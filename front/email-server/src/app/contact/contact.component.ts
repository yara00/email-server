import { Component, Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {LogInComponent} from 'src/app/log-in/log-in.component';
import { Router } from '@angular/router';
@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
@Injectable()
export class ContactComponent implements OnInit {
  username = "";
  email = [];
  search = "";
  validation = "";
  respond : any;
  contacts : any;
  id = 0;
  num = 0;
  editName = "";

  login = new  LogInComponent(null, null);

  usernameEdit = "";
  emailEdit = [];

  // carry attributes
  usernameCarry = "";
  emailCarry = [];

  constructor(private http: HttpClient, private router: Router) { }
  
  newClass = new LogInComponent(this.http, this.router);
  
  ngOnInit(): void {
    console.log(this.username, this.email);
    this.loading();
  }

  loading(){
    this.http.get('http://localhost:8080/contact/load', {
      responseType: 'json',
      params:{
        userName: this.login.getUser()
      },
      observe:'response'
    }).subscribe(response => {
      this.contacts = response.body;
      this.show();
    })
    
    
  }

  createContact() {
    document.getElementById('add-contact').style.display = "block";
  }
  
  /*
  const params = new URLSearchParams();
        params.set('username', this.newClass.getUser());
        
        const options = new RequestOptions({
          headers: this.getAuthorizedHeaders(),
          responseType: ResponseContentType.Json,
          params: params,
          withCredentials: false
        });
  */

  save() {
    console.log("d5lt save")
    this.http.post('http://localhost:8080/contact/add',{name : this.username, emails: [this.email, "e"]}, {
      responseType: 'text', 
      params: {
        userName: this.login.getUser()
      },
      observe: 'response'
    }).subscribe( response => {
      var validation = response.body
      console.log(response.body)
    if (validation === "true") {
      document.getElementById('add-contact').style.display = "none";
      this.contacts = []
      this.contacts.push({name : this.username, emails: this.email});
      console.log(this.contacts)
      this.show()   
    
    }
    else {
      document.getElementById('error').style.display = "error"
    }
    })
  }
  
  cancel() {
    document.getElementById('add-contact').style.display = "none";
  }

  show() {
    document.getElementById("add").innerHTML = "";
    for (let i = 0; i < this.contacts.length; i++){
      this.elementCreation(this.contacts[i].name, this.contacts[i].emails);
    }
  }

  elementCreation(name: string = "", email = []) {
    let button = document.createElement('button');
    let h = document.createElement('h3');
    let deleteButton = this.createDeleteButton();
    let editButton = this.createEditButton();
    button.appendChild(h);
    for (let i = 0; i < email.length; i++){
      let p = document.createElement('p');
      p.innerText = email[i];
      p.style.fontSize = '12px';
      button.appendChild(p);
    }
    button.appendChild(deleteButton);
    button.appendChild(editButton);

    h.innerText = name;
    h.style.fontSize = '20px';

    // setting the button
    button.setAttribute('id', this.id.toString());

    // hover
    button.setAttribute('style',
      `width: 300px; 
      background-color: rgba(255, 255, 255, 0.5);
      border: none;
      padding: 15px 32px;
      text-align: start;
      text-decoration: none;
      position: relative`);

    button.addEventListener('mouseover', function (event) {
      button.style.opacity = "70%";
    })
    button.addEventListener("mouseleave", function () {
      button.style.opacity = "100%";
    })

    document.getElementById('add').style.justifyContent = 'start';
    document.getElementById('add').appendChild(button);
    this.id++;
  }

  createDeleteButton() {
    // Delete Button
    let id = "";
    let deleteButton = document.createElement('button');
    let image = document.createElement('i');
    image.style.fontSize = "20px";
    image.setAttribute('class', 'fas fa-trash-alt')
    deleteButton.setAttribute('style', `
      width: 40px;
      height: 40px;
      display: flex;
      align-items: center;
      justify-content: center;
      position: absolute;
      right: 2%;
      top: 0%;
      border: 0px;
      color: black;
    `);
      
    deleteButton.addEventListener('mouseover', function (event) {
      deleteButton.style.color = "red";
    })
    deleteButton.addEventListener("mouseleave", function () {
      deleteButton.style.color = "black";
    })

    deleteButton.addEventListener('click', function () {
      id = this.parentElement.getAttribute('id');
      this.parentElement.remove();
      console.log(id);
    })
    deleteButton.appendChild(image);
    return deleteButton;
  }

  createEditButton() {
    let edit = document.createElement("button");
    let edit_image = document.createElement('i');
    edit_image.setAttribute('class', 'fas fa-edit');
    edit.append(edit_image);
    edit_image.style.fontSize = "20px";
    edit.setAttribute('style', `
      width: 40px;
      height: 40px;
      display: flex;
      align-items: center;
      justify-content: center;
      position: absolute;
      right: 15%;
      border: 0px;
      color: black;
      top: 0%;
    `)
    edit.onclick = this.editing;
    return edit;
  }

  editing() {
    document.getElementById('username-edit').setAttribute('value', this.username);
    document.getElementById('email-edit').setAttribute('value', '');
    document.getElementById('edit-contact').style.display = "block";
  }

  addEmail() {
    let input = document.createElement('input');
    let label = document.createElement('label');
    label.innerText = "Email";
    label.style.width = "80px";
    input.setAttribute('id', `email${this.num}`);
    label.setAttribute('for', 'email${num}')
    input.setAttribute("type", "text");
    input.setAttribute('style',
      `border: none;
      background: transparent;
      outline: none;
      color: black;
      font-size: 16px;
      width: 250px;
      `);

    let btn = document.getElementById('add-email-btn')
    let line = document.createElement('hr');
    btn.parentNode.insertBefore(label, btn);
    btn.parentNode.insertBefore(input, btn);
    btn.parentNode.insertBefore(line, btn);
    this.num++;
  }

  cancelEditing() {
    document.getElementById('edit-contact').style.display = "none";
  }
    saveEditing() {
    document.getElementById('edit-contact').style.display = "none";
    this.http.post('http://localhost:8080/contact/edit',{body : [{edit :  {name: this.editName, emails: this.emailEdit}}, {prev :  {name: this.username, emails: this.email}}]},
    {responseType: 'text',
      params: {userName: this.login.getUser()},
    }).subscribe(response => {
      console.log(response)
  })
  }




  selectingContact() {
    
  }
  
  searching() {
    this.http.get('http://localhost:8080/filter/search', {
      responseType: 'text',
      params: {
        search: this.search,
      },
      observe:'response'
    }).subscribe(response => {
      this.respond = response.body;
    })
    this.contacts = JSON.parse(this.respond);
    this.show();
  }

  sorting(text: string = "") {
    this.http.get('http://localhost:8080/sort', {
      responseType: 'json',
      params:{
        userName:this.login.getUser(),
        fileName: "Contacts",
        criteria: "name",
        page: 0
      },
      observe:'response'
    }).subscribe(response => {
      this.respond = response.body;
      this.contacts = this.respond.mails;
      console.log(this.contacts)
      this.show();
    })

  }
}

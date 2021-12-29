import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {PageEvent} from '@angular/material/paginator';
import { LogInComponent } from '../log-in/log-in.component';
import { FileService } from '../file.service';
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  constructor(private http: HttpClient, private router: Router,  private fileService: FileService) { }
  newApp = new AppComponent(this.fileService);
  login = new  LogInComponent(null, null);
  length = 0;
  pageSize = 10;
  sortFlag = false;
  importance = 3;
  attachDown = [""];
  select = [0,0,0,0,0,0,0,0,0,0]
  attr = "";
  EditName = ""
  // MatPaginator Output
  pageEvent: PageEvent;
  getPageIndex(event: PageEvent){
    this.id = 0;
    if(!this.sortFlag){
    if(this.folderName === "Search") this.search(event.pageIndex)
    else this.loading(this.folderName, event.pageIndex)}

  }
  ngOnInit(): void {
  }

  delete(){
    this.http.delete("", {

      params:{},
      body: {},
    }).subscribe()
  }

  id = 0;
  email = "";
  showEmails :any;
  respond = "";
  // compose data
  to : string;
  subject = "";
  emailText = "";
  searchText = "";
  folderName = "";


  

  // dialog folder data
  name = "";
  // dialog contact data
  contact = "";
  contact_email = "";
  // filter data
  filterName = "";
  filterFolder = "";
  content : any;
  
  //Loading Request Donnnnnnnnnnne
  loading(folder: string = "", page: number) {
    this.id = 0;
    this.sortFlag = false;
    this.folderName = folder;
    this.http.get('http://localhost:8080/sort', {
      responseType: 'json',
      params:{
        userName: this.login.getUser(),
        fileName: this.folderName,
        criteria:"date",
        page: page
      },
      observe:'response',
    }).subscribe(response => {
      console.log(response.body);
      this.content = response.body;
      this.length = this.content.messagesNo  
      this.showEmails = this.content.mails
      console.log(this.showEmails)
      this.show();  
    })
  }

  formatMessage(message : any){
    var messageHeader = message.messageHeader

  }

  show() {
    document.getElementById('emails').innerHTML = '';
    if (this.showEmails.length === 0) {
      let empty = document.createElement('div');
      let image = document.createElement('i');
      image.setAttribute('class', 'far fa-envelope');
      image.style.paddingRight = '20px';
      empty.innerText = 'Nothing new';
      document.getElementById('emails').appendChild(image);
      document.getElementById('emails').appendChild(empty);
      document.getElementById('emails').style.justifyContent = 'center'
    }
    else {
      for (let i = 0; i < this.showEmails.length; i++) {
      
        this.settingMail(this.showEmails[i].messageHeader.sender,
           this.showEmails[i].messageHeader.receivers, 
           this.showEmails[i].messageHeader.subject,
           this.showEmails[i].messageHeader.date,
           this.showEmails[i].messageHeader.priority,
            this.showEmails[i].messageBody.body,
            //this.showEmails[i].messageAttachment 
           );
           this.attachDown.push(this.showEmails[i].messageAttachment)
      }
    }
  }

settingMail(sender: string = "", receivers: string = "", subject: string = "",
 date: string = "", priority: number, body: string ) {
    let button = document.createElement('button');
    let h = document.createElement('h3');
    let p = document.createElement('p');
    let check = document.createElement('input');
    check.setAttribute('type', 'checkbox');
    check.setAttribute('style', `
      position: absolute;
      left: 2%;
      top: 20%`)
    check.setAttribute('id', `c${this.id}`);
    // check.setAttribute('checked', 'false');
    let deleteButton = this.createDeleteButton();
    button.setAttribute('class', 'mail');
    button.appendChild(deleteButton);
    button.appendChild(check);
    // check.addEventListener("change", (e: event)=>this.check((e.target)<HTMLInputElement>).checked);
    check.addEventListener('change', (e:Event) => this.check(e));
    h.innerText = sender;
    h.style.fontSize = '20px';
    p.innerText = subject;
    p.style.fontSize = '12px';
    button.appendChild(h);
    check.setAttribute('id', `c${this.id}`); 
    // setting the button
    button.appendChild(p);
    button.setAttribute('id', this.id.toString());
    button.setAttribute('class', 'button');

    // hover
    button.setAttribute('style',
      `width: 370px; 
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
    // handling opening
    button.addEventListener('click', function (e) {
      if (e.offsetX !== 0 && e.offsetX > 11) {
        document.getElementById('text-open').innerText = body;
        document.getElementById('to-open').setAttribute('value', receivers);
        document.getElementById('subject-open').setAttribute('value', subject);
        document.getElementById('from-open').setAttribute('value', sender);
        document.getElementById('date').setAttribute('value', date);
        document.getElementById('importance').setAttribute('value', priority.toString());
        document.getElementById('compose').style.display = 'none';
        document.getElementById('open-mail').style.display = "block";
        //document.getElementById('attach1').innerText = this.attachDown[parseInt(this.id)]
      }
    })
    
    document.getElementById('emails').style.justifyContent = 'start';
    document.getElementById('emails').appendChild(button);
    this.id++;
  }
  bulkDelete() {
    var mailsToDelete= [];
    for (let i = 0; i < this.select.length; i ++){
      if(this.select[i] == 1){
        document.getElementById(i.toString()).remove();
        mailsToDelete.push(this.showEmails[i])
        this.select[i] = 0;
      }
    }
    this.delReq(mailsToDelete)
    //this.loading(this.filterName, 0);

  }
  
  editHelper() {
    if (this.EditName == "") {
      document.getElementById('folderError').style.display = "block";
    }
    else {
      this.http.get('http://localhost:8080/filter', {
        responseType: 'text',
        params: {
          folder: this.name,
          newName: this.EditName
        },
        observe:'response'
      }).subscribe(response => {
        if (response.body == 'true') {
          document.getElementById('edit').style.display = 'none';
        }
        else {
          document.getElementById('folderError').style.display = "block";
        }
      })
    }
  }


  // compose functions
  insertAttach() {
    
  }
  newClass = new LogInComponent(this.http, this.router);
  //Request send Doneeeeeeeeeeeeeeeee
  sendCompose() {
    if (this.to === "") {
      console.log(true);
      document.getElementById('error').style.display = 'block';
    }
    else {
      console.log(this.attachDown,"koko")
      this.http.post('http://localhost:8080/mail/send',{}, {
      responseType: 'text',
      params: {
        sender: this.newClass.getUser(),
        receivers: this.to,
        subject: this.subject,
        body: this.emailText,
        priority: this.importance,
        attachment: this.attachDown
      },
      observe: 'response'
      }).subscribe(response => {
      this.to = "";
      this.emailText = "";
      this.subject = "";
      this.importance = 3
      console.log(this.attachDown,"lala")

      }
      )
      document.getElementById('compose').style.display = 'none';
    }
  }

  draft(){
    if(this.to === "" && this.emailText === "" && this.subject=== "") return;
    this.http.post('http://localhost:8080/mail/draft',{}, {
      params: {
        sender: this.newClass.getUser(),
        receivers: this.to,
        subject: this.subject,
        body: this.emailText,
        priority: this.importance,
      },
      }).subscribe(fun => {
      this.to = "";
      this.emailText = "";
      this.subject = "";
      this.importance = 3
      }
      )
      document.getElementById('compose').style.display = 'none';
    }

  

  discard() {
    this.to = "";
    this.emailText = "";
    this.subject = "";
    this.importance = 3
    document.getElementById('compose').style.display = 'none';
  }

  newMail() {    
    document.getElementById('open-mail').style.display = 'none';
    document.getElementById('compose').style.display = 'inline-flex';
    //document.getElementById('from').setAttribute('value', this.email);
  }


  // add folder functions
  closeFolder() {
    document.getElementById("folder").style.display = "none"
  }

  openFolder() {
    document.getElementById("folder").style.display = "block"
  }

  addFolder() {
    if (this.name === "") {
      document.getElementById("folder").style.display = "block";
      document.getElementById('folder-error').style.display = "block";
    }
    else{
      this.addFolderReq();     
  }
  
  }

  
  addFolderReq(): void{
    this.http.post('http://localhost:8080/folder/create',{}, {
      responseType: 'text',
      params: {
        userName: this.login.getUser(),
        fileName: this.name,
      },
      observe: 'response'
      }).subscribe(response => {
        if(response.body === "true"){
        document.getElementById("folder").style.display = "none"
        let element = document.createElement('button');
        let control = document.getElementById('control-folder');
        let h = document.createElement('h3');
        let edit = this.createEditButton();
        let deleteButton = this.createDeleteButton();
        h.innerText = this.name;
        element.setAttribute('style',
          `width: 350px; 
          background-color: rgba(255, 255, 255, 0.5);
          border: none; 
          padding: 10px 20px;
          margin: 5px;
          text-align: start;
          text-decoration: none;
          `);
          element.addEventListener('mouseover', function () {
          element.style.opacity = "70%";
        })
        element.addEventListener("mouseleave", function () {
          element.style.opacity = "100%";
        })
        element.append(deleteButton);
        element.append(edit);
        element.appendChild(h);
        element.addEventListener('click', () => this.loading(element.innerText, 0));

        control.parentNode.insertBefore(element, control);
      }
      else {
        document.getElementById("folder").style.display = "block";
        document.getElementById('folder-error').style.display = "block";
      }
      }
      )
    

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
      right: 10%;
      border: 0px;
      color: black;
    `)
    edit.addEventListener('click', function () {
      this.name = this.parentElement.innerText;
      document.getElementById('edit').style.display = "block";
    })

    return edit;
  }

  check(e: Event) {  
  var id = parseInt((<HTMLButtonElement> e.target).parentElement.id) ;

   if((<HTMLInputElement> e.target).checked == false)this.select[id] = 0;
   else this.select[id] = 1
     }

  createDeleteButton() {
     // Delete Button
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
      this.parentElement.remove();
    })
    deleteButton.appendChild(image);
    return deleteButton;
  }


  // add contact functions
  closeContact() {
    document.getElementById("contact").style.display = "none"
  }

  openContact() {
    document.getElementById("contact").style.display = "block"
  }

  addContact() {
    if (this.contact === ""){
      document.getElementById("contact-error").style.display = "block";
    }
    else if (this.contact_email === "") {
      document.getElementById("contact-email-error").style.display = "block";
    }
    else {
      document.getElementById("contact").style.display = "none"
      let element = document.createElement('button');
      let control = document.getElementById('control-contact');
      let h = document.createElement('h3');
      let p = document.createElement('p');
      h.innerText = this.contact;
      p.innerText = this.contact_email;

      element.setAttribute('style',
        `width: 370px; 
        background-color: rgba(255, 255, 255, 0.5);
        border: none; 
        padding: 10px 20px;
        margin: 5px;
        text-align: start;
        text-decoration: none;
        position: relative`);

        // delete button
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
      right: 0%;
      top: 0%;
      border: 0px;
    `);
    
    deleteButton.addEventListener('mouseover', function (event) {
      deleteButton.style.color = "red";
    })
    deleteButton.addEventListener("mouseleave", function () {
      deleteButton.style.color = "black";
    })

    deleteButton.appendChild(image);

    deleteButton.addEventListener('click', function () {
      this.parentElement.remove();
    })

    element.appendChild(h);
    element.appendChild(p);
    element.appendChild(deleteButton);
      
      // handling hover
        element.addEventListener('mouseover', function (event) {
          element.style.opacity = "70%";
        })
        element.addEventListener("mouseleave", function () {
          element.style.opacity = "100%";
        })
      
      control.parentNode.insertBefore(element, control);
    }
  }

  // Search operations
  search(page : number) {
    this.folderName = "Search"
    this.http.get('http://localhost:8080/filter/search', {
      responseType: 'json',
      params: {
        userName : this.login.getUser(),
        key: this.searchText,
        page: page
      },
      observe:'response'
    }).subscribe(response => {
      this.content = response.body;
      this.length = this.content.messagesNo
      console.log(this.content)
    })
    this.show();
  }
// deleteMailRequst Doneeeeeeeeeeeeeeee,
  delReq(mailsToDelete){
    this.http.delete("http://localhost:8080/mail/delete", {
      body : {msgs: mailsToDelete},
      params :{
        fileName : this.folderName,
        userName : this.login.getUser()
      }
    }).subscribe()
  }

  // the user's folder
  showFolders() {
    document.getElementById('your-folders').style.display = "block";
    this.http.get('http://localhost:8080/folder/load', {
      responseType: 'json',
      params:{
        userName: this.login.getUser(),
      },
      observe:'response',
    }).subscribe(response => {
      console.log(response.body);
      this.content = response.body;
      for(let i = 0; i < this.content.length; i++){
        document.getElementById("folder").style.display = "none"
        let element = document.createElement('button');
        let control = document.getElementById('control-folder');
        let h = document.createElement('h3');
        let edit = this.createEditButton();
        let deleteButton = this.createDeleteButton();
        h.innerText = this.content[i].fileName;
        element.setAttribute('style',
          `width: 350px; 
          background-color: rgba(255, 255, 255, 0.5);
          border: none; 
          padding: 10px 20px;
          margin: 5px;
          text-align: start;
          text-decoration: none;
          `);
          element.addEventListener('mouseover', function () {
          element.style.opacity = "70%";
        })
        element.addEventListener("mouseleave", function () {
          element.style.opacity = "100%";
        })
        element.append(deleteButton);
        element.append(edit);
        element.appendChild(h);
        element.addEventListener('click', () => this.loading(element.innerText, 0));
        control.parentNode.insertBefore(element, control)

      }
    })
    
  }
  closeYFolders() {
    document.getElementById("your-folders").style.display = 'none';

  }

  // Sorting
  sort(type: string = "", page: number) {
    this.sortFlag = true;
    this.http.get('http://localhost:8080/sort', {
      responseType: 'json',
      params: {
        userName: this.newClass.getUser(),
        fileName: this.folderName,
        criteria: type,
        page:page
      },
      observe:'response'
    }).subscribe(response => {
      this.content = response.body;
      console.log(response.body)
    })
  }

  // filter
  filter(attr: string = "") {
    this.settingFilter(attr);
    console.log

  }

  settingFilter(attr: string) {
    document.getElementById('filter').style.display = "block";
    if (attr === "sender") {
      document.getElementById('filter-title').innerText = "Sender"
      this.attr = 'sender'
    }
    else if (attr === "subject") {
      document.getElementById('filter-title').innerText = "Subject"
      this.attr = 'subject'

    }
  }

  addFilter() {
    if (this.filterName == "") {
      document.getElementById('filter-error').style.display = "block";
    }
    else if (this.filterFolder == "") {
      document.getElementById("filter-folder-error").style.display = "block";
    }
    else {
      document.getElementById('filter').style.display = "none";
      this.name = this.filterFolder;
      this.http.post('http://localhost:8080/filter/'+ this.attr,{}, {
        params: {
          userName: this.login.getUser(),
          fileName: this.filterFolder,
          key:this.filterName
        },
        }).subscribe()

    }
  }

  closeFilter() {
    document.getElementById('filter').style.display = "none";
  }
}

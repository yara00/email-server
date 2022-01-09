import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class FileService {
  private server = 'http://localhost:8080';

  constructor(private http: HttpClient) {}
  editDataDetails = "";
  user = ""
  // define function to upload files
  upload(formData: FormData): Observable<HttpEvent<string[]>> {
    return this.http.post<string[]>(`${this.server}/file/upload`, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  // define function to download files
  download(filename: string): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.server}/file/download/${filename}/`, {
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    });
  }

  private messageSource = new  BehaviorSubject(this.editDataDetails);
  currentMessage = this.messageSource.asObservable();
  changeMessage(message: string) {
  this.messageSource.next(message)
  console.log("fe service", this.currentMessage)
  console.log("fe service2", this.messageSource.value)
  this.user = this.messageSource.value
  return this.messageSource.value
  }

  returnMessage() {
    console.log("d5lt return fe service", this.user)
    return this.user
    }
  
}
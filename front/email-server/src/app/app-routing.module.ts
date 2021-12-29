import { ContactComponent } from './contact/contact.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { MainComponent } from './main/main.component';

const routes: Routes = [
  {path: 'login', component: LogInComponent},
  { path: 'main', component: MainComponent },
  { path: 'signup', component: SignUpComponent },
  {path: 'contact', component: ContactComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LogInComponent, MainComponent, SignUpComponent, ContactComponent]

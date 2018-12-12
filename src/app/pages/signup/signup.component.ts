import { Component, OnInit } from '@angular/core';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../_services/authentication.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {


    model:any = {};
    alert:IAlert = null;
    loading = false;
    returnUrl: string;


    constructor(
      private router: Router,
      public authenticationService: AuthenticationService) { }

    ngOnInit() {
        var body = document.getElementsByTagName('body')[0];
        body.classList.add('login-page');

        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
    }
    ngOnDestroy(){
        var body = document.getElementsByTagName('body')[0];
        body.classList.remove('login-page');

        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.remove('navbar-transparent');
    }

    closeAlert() {
      this.alert = null;
    }

    signup() {

      this.loading = true;
      this.authenticationService.signup(this.model)
        .subscribe(response => {
          console.log(response);
          if(response === null) {
            this.router.navigate(['/profile']);
          } else {
            this.alert = response;
          }
        }, error => {
          this.alert = {
            id: 3,
            type: 'warning',
            strong: 'Error ' + error.status +' !',
            message: 'caused by backend service.',
            icon: 'ui-1_bell-53'
          };
        });
    }
}

export interface IAlert {
    id: number;
    type: string;
    strong?: string;
    message: string;
    icon?: string;
}

import { Component, OnInit } from '@angular/core';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../../_services/authentication.service';
import { CartService } from '../../_services/cart.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

      email:string = "";
      password:string = "";

      alert:IAlert = null;

      data : Date = new Date();
      focus;
      focus1;

      loading = false;
      returnUrl: string;

      constructor(
        private route: ActivatedRoute,
        private router: Router,
        private cartService: CartService,
        private authenticationService: AuthenticationService) { }

      ngOnInit() {
          var body = document.getElementsByTagName('body')[0];
          body.classList.add('login-page');

          var navbar = document.getElementsByTagName('nav')[0];
          navbar.classList.add('navbar-transparent');

        // reset login status
        // this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
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

    login() {
      this.loading = true;
      this.authenticationService.login(this.email, this.password)
      .subscribe(response => {
        if(response === null){
          this.cartService.addItems();
          this.router.navigate([this.returnUrl]);
        } else {
          this.alert = response;
          this.loading = false;
        }
      }, error => {
        this.loading = false;
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

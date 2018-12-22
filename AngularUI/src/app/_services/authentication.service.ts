import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { Router, ActivatedRoute } from '@angular/router';

import { CartService } from './cart.service';

@Injectable()
export class AuthenticationService {
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private cart: CartService,
        private http: HttpClient) { }

    login(email: string, password: string) {

        return this.http.post<any>('http://18.210.39.123:8080/api/auth/login', { email: email, password: password })
            .map(user => {
                if(user.message === "LOGGED_IN") {
                  localStorage.setItem('currentUser', JSON.stringify(user.body));
                  return null;
                } else if(user.message === "USER_DOES_NOT_EXIST") {
                  return {
                      id: 2,
                      strong: 'Please Register!',
                      type: 'info',
                      message: 'User does not exist.',
                      icon: 'travel_info'
                  };
                } else if(user.message === "WRONG_PASSWORD") {
                  return {
                    id: 4,
                    type: 'danger',
                    strong: 'Oops!',
                    message: 'Wrong Password.',
                    icon: 'objects_support-17'
                  };
                }
            });
    }

    signup(user : any) {
      return this.http.post<any>('http://18.210.39.123:8080/api/auth/signup', user)
        .map(user => {
          if(user.message === "SIGNED_UP") {
              localStorage.setItem('currentUser', JSON.stringify(user.body));
              return null;
          } else if(user.message === "USER_ALREADY_EXIST") {
            return {
                id: 2,
                strong: 'Try again!',
                type: 'info',
                message: 'User already exist.',
                icon: 'travel_info'
            };
          }
        });
    }

    isLoggedIn() {
      if (localStorage.getItem('currentUser')) {
          // logged in so return true
          return true;
      }
      return false;
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.router.navigate(['/']);
    }
}

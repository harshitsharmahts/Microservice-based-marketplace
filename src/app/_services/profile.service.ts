import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import { CookieService } from './cookie.service'

@Injectable()
export class ProfileService {

    constructor(
        private cookie: CookieService,
        private http: HttpClient) { }

    getUserInfo() {
      return this.http.get('http://18.210.39.123:8080/api/user-rest/user/' + this.cookie.getEmail());
    }
}

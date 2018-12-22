import { Injectable } from '@angular/core';
import { RequestOptions, Headers } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from './cookie.service';

@Injectable()
export class CartService {

    constructor(
        private http: HttpClient,
        private cookie: CookieService) { }

    getCartItems() {
      return this.http.post('http://18.210.39.123:8080/api/cart/items/' + this.cookie.getEmail(),{})
        .map((response:JSONResponse) => {
          return response.body;
        });
    }

    getPurchasedItems() {
      return this.http.post('http://18.210.39.123:8080/api/cart/purchased-items/' + this.cookie.getEmail(),{});
    }

    addItemToCart(id:string) {
      return this.http.post('http://18.210.39.123:8080/api/cart/',{userName: this.cookie.getEmail(), itemId: id});
    }

    deleteItemFromCart(id:string) {
      return this.http.request('delete', 'http://18.210.39.123:8080/api/cart/', { body: { userName: this.cookie.getEmail(), itemId: id} });
    }

    purchase() {
      return this.http.request('put','http://18.210.39.123:8080/api/cart/purchase/' + this.cookie.getEmail(), { body: {} });
    }
    addItems() {
      if(localStorage.getItem('localCart')){
        return this.http.post('http://18.210.39.123:8080/api/cart/bulk',{userName: this.cookie.getEmail(), itemId: this.cookie.getItemsID()})
        .subscribe(data => {
          localStorage.removeItem('localCart');
        });
      }
    }
}

export interface JSONResponse {
    status:boolean;
    body:any;
}

import { Injectable } from '@angular/core';

@Injectable()
export class CookieService {

    constructor() {
    }

    getFullName() {
      return JSON.parse(localStorage.getItem('currentUser')).fullName;
    }

    getEmail() {
      return JSON.parse(localStorage.getItem('currentUser')).email;
    }

    initCartWithoutLogin() {
      if(!localStorage.getItem('localCart')) {
        localStorage.setItem('localCart', JSON.stringify({
          invoice : 0,
          items : [

          ]
        }));
      }
    }

    addItemToCart(item : any) {
      let cart = JSON.parse(localStorage.getItem('localCart'));
      cart.items.push(item);
      localStorage.setItem('localCart',JSON.stringify(cart));
    }

    removeFromCart(itemId : string) {
      let cart = JSON.parse(localStorage.getItem('localCart'));
      let removeIndex = cart.items.map(function(item) { return item.id; }).indexOf(itemId);
      cart.items.splice(removeIndex, 1);
      localStorage.setItem('localCart',JSON.stringify(cart));
    }

    getCartItems() {
      let cart = JSON.parse(localStorage.getItem('localCart'));
      let totalPrice = 0.0;
      cart.items.map(function(item) {
        return item.price;
      }).forEach(function(price) {
        totalPrice += price;
      });
      cart.invoice = totalPrice;
      return cart;
    }

    getItemsID() {
      let cart = JSON.parse(localStorage.getItem('localCart'));
      return cart.items.map(function(item){return item.id;});
    }
}

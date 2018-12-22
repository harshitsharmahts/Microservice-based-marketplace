import { Component, OnInit } from '@angular/core';
import * as Rellax from 'rellax';
import { CartService } from '../../_services/cart.service';


@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {

  historyItems:any;
  data : Date = new Date();
  focus;
  focus1;

  constructor(private cartService : CartService) {
    this.loadCartItems();
  }

  ngOnInit() {
    var rellaxHeader = new Rellax('.rellax-header');

    var body = document.getElementsByTagName('body')[0];
    body.classList.add('cart-page');
    var navbar = document.getElementsByTagName('nav')[0];
    navbar.classList.add('navbar-transparent');
  }
  ngOnDestroy(){
    var body = document.getElementsByTagName('body')[0];
    body.classList.remove('cart-page');
    var navbar = document.getElementsByTagName('nav')[0];
    navbar.classList.remove('navbar-transparent');
  }

  private loadCartItems() {
    this.cartService.getPurchasedItems()
    .subscribe(response =>{
      this.historyItems = response;
    });
  }
}

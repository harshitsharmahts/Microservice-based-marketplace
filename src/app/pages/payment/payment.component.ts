import { Component, OnInit } from '@angular/core';
import * as Rellax from 'rellax';
import { CartService } from '../../_services/cart.service';
import { CookieService } from '../../_services/cookie.service';
import { AuthenticationService } from '../../_services/authentication.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-pay',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {

  cartItems:any;
  returnUrl:string;
  cred:any = {};
  modalReference:any;

  constructor(private cartService : CartService,
              private modalService: NgbModal,
              public authentication : AuthenticationService,
              private cookie : CookieService,
              private router : Router) {
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
    if(this.authentication.isLoggedIn()) {
      this.cartService.getCartItems()
      .subscribe(response =>{
        this.cartItems = response;
      });
    } else {
      this.cartItems = this.cookie.getCartItems();
    }
  }

  purchaseNow() {
    if(this.authentication.isLoggedIn()) {
      this.cartService.purchase()
        .subscribe(response => {
          this.modalReference.close();
          this.router.navigate(['/history']);
        });
    }
  }
}

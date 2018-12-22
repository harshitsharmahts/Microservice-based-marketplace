import { Component, OnInit } from '@angular/core';
import * as Rellax from 'rellax';
import { CartService } from '../../_services/cart.service';
import { CookieService } from '../../_services/cookie.service';
import { AuthenticationService } from '../../_services/authentication.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  cartItems:any;
  returnUrl:string;
  cred:any = {};
  modalReference:any;

  constructor(private cartService : CartService,
              private modalService: NgbModal,
              public authentication : AuthenticationService,
              private cookie : CookieService,
              private router : Router) {

    if(!this.authentication.isLoggedIn()) {
      this.cookie.initCartWithoutLogin();
    }
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

  open(content, type, modalDimension) {
      if (modalDimension === 'sm' && type === 'modal_mini') {
          this.modalReference = this.modalService.open(content, { windowClass: 'modal-mini modal-danger', size: 'sm' });
      } else if (modalDimension == undefined && type === 'Login') {
        this.modalReference = this.modalService.open(content, { windowClass: 'modal-login modal-danger' });
      }
  }

  closeModal() {
    this.modalReference.close();
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

  cartLogin() {
    this.authentication.login(this.cred.email, this.cred.password)
      .subscribe(response => {
        if(response == null) {
          this.cartService.addItems();
          this.closeModal();
          this.router.navigate(['/payment'], {queryParams:{loggedIn:true}});
        } else {
          this.cred.message = response.strong + response.message;
        }
      });
  }

  deleteItem(itemId : string) {
    if(this.authentication.isLoggedIn()) {
      this.cartService.deleteItemFromCart(itemId)
        .subscribe(response => {
          this.loadCartItems();
      });
    } else {
      console.log();
      this.cookie.removeFromCart(itemId);
      this.loadCartItems();
    }
  }
}

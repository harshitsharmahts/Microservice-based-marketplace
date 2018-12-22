import { Component, OnInit, Renderer, OnDestroy } from '@angular/core';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { NgbAccordionConfig } from '@ng-bootstrap/ng-bootstrap';
import * as Rellax from 'rellax';
import { InventoryService } from '../_services/inventory.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { CartService } from '../_services/cart.service';
import { AuthenticationService } from '../_services/authentication.service';
import { CookieService } from '../_services/cookie.service';

@Component({
    selector: 'app-inventory',
    templateUrl: './inventory.component.html',
    styles: [`
    ngb-progressbar {
        margin-top: 5rem;
    }
    `]
})

export class InventoryComponent implements OnInit, OnDestroy {

    items : any;
    modalItem : any;

    modalReference:any;

    data : Date = new Date();

    constructor(private modalService: NgbModal,
      private renderer : Renderer,
      config: NgbAccordionConfig,
      private inventoryService:InventoryService,
      private cartService:CartService,
      private cookie:CookieService,
      private authentication:AuthenticationService,
      ) {
        if(!this.authentication.isLoggedIn()) {
          this.cookie.initCartWithoutLogin();
        }
        this.loadItems();
        config.closeOthers = true;
        config.type = 'info';
    }

    ngOnInit() {
        var rellaxHeader = new Rellax('.rellax-header');

        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
        var body = document.getElementsByTagName('body')[0];
        body.classList.add('index-page');
    }
    ngOnDestroy(){
        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.remove('navbar-transparent');
        var body = document.getElementsByTagName('body')[0];
        body.classList.remove('index-page');
    }

    closeResult: string;

    open(content, type, modalDimension, modalItem) {
        this.modalItem = modalItem;
        this.modalReference = this.modalService.open(content);
    }

    loadItems() {
      this.inventoryService.getItems()
        .subscribe(response => {
          this.items = response;
          console.log();
        });
    }

    addToCart(itemId:string) {
      if(this.authentication.isLoggedIn()) {
        this.cartService.addItemToCart(itemId)
          .subscribe(response => {
              console.log(response);
          });
      } else {
        let arr = this.items.body;
        this.cookie.addItemToCart(arr.find(function(o){
          return o.id === itemId;
        }));
      }
      this.modalReference.close();
    }
}

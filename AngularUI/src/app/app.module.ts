import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // this is needed!
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';


import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app.routing';
import { InventoryModule } from './inventory/inventory.module';
import { PagesModule } from './pages/pages.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './shared/navbar/navbar.component';

import { LoginGuard } from './_guards/login.guard';
import { LogoutGuard } from './_guards/logout.guard';


import { AuthenticationService} from './_services/authentication.service';
import { CookieService} from './_services/cookie.service';
import { CartService} from './_services/cart.service';

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent
    ],
    imports: [
        BrowserAnimationsModule,
        NgbModule.forRoot(),
        FormsModule,
        RouterModule,
        HttpClientModule,
        AppRoutingModule,
        PagesModule,
        InventoryModule
    ],
    providers: [
      LoginGuard,
      LogoutGuard,
      AuthenticationService,
      CookieService,
      CartService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }

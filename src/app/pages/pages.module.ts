import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NouisliderModule } from 'ng2-nouislider';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { JWBootstrapSwitchModule } from 'jw-bootstrap-switch-ng2';
import { AgmCoreModule } from '@agm/core';

import { CartComponent } from './cart/cart.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ProfileComponent } from './profile/profile.component';
import { PagesComponent } from './pages.component';
import { HistoryComponent } from './history/history.component';


import { ProfileService} from '../_services/profile.service';

@NgModule({
    declarations: [
        ProfileComponent,
        PagesComponent,
        CartComponent,
        LoginComponent,
        SignupComponent,
        HistoryComponent
    ],
    imports: [
        CommonModule,
        FormsModule,
        NgbModule,
        RouterModule,
        NouisliderModule,
        JWBootstrapSwitchModule,
        AgmCoreModule.forRoot({
            apiKey: 'YOUR_KEY_HERE'
        })
    ],
    providers: [
      ProfileService
    ]
})
export class PagesModule { }

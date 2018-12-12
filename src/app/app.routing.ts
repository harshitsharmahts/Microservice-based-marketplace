import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { InventoryComponent } from './inventory/inventory.component';
import { CartComponent } from './pages/cart/cart.component';
import { HistoryComponent } from './pages/history/history.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { ProfileComponent } from './pages/profile/profile.component';

import { LoginGuard } from './_guards/login.guard';
import { LogoutGuard } from './_guards/logout.guard';


const routes: Routes =[
    { path: '', redirectTo: 'index', pathMatch: 'full' },
    { path: 'index',                component: InventoryComponent},
    { path: 'cart',     component: CartComponent },
    { path: 'history',     component: HistoryComponent, canActivate: [LoginGuard]},
    { path: 'login',       component: LoginComponent, canActivate: [LogoutGuard]},
    { path: 'signup',       component: SignupComponent, canActivate: [LogoutGuard]},
    { path: 'profile',     component: ProfileComponent, canActivate: [LoginGuard]}
];

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        RouterModule.forRoot(routes)
    ],
    exports: [
    ],
})
export class AppRoutingModule { }

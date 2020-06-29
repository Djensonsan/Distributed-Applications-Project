import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NavbarComponent} from './views/navbar/navbar.component';
import {FormComponent} from './views/customer/form/form.component';
import {ListComponent} from './views/customer/list/list.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {PostService} from './services/post.service';
import {CustomerComponent} from './views/customer/customer.component';
import {OrderComponent} from './views/order/order.component';
import {ShopComponent} from './views/shop/shop.component';
import {RouterModule} from '@angular/router';
import {HomeComponent} from './views/home/home.component';
import {LoginComponent} from './views/login/login.component';
import {AuthService} from './services/auth.service';
import {AuthGuard} from './guards/auth.guard';
import {NgxSoapModule} from 'ngx-soap';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FormComponent,
    ListComponent,
    CustomerComponent,
    OrderComponent,
    ShopComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    NgxSoapModule,
    RouterModule.forRoot([
      {path: '', component: HomeComponent},
      {path: 'customer', component: CustomerComponent},
      {path: 'shop', component: ShopComponent},
      {path: 'order', component: OrderComponent, canActivate: [AuthGuard]},
      {path: 'login', component: LoginComponent}
    ])
  ],
  providers: [PostService, AuthService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}

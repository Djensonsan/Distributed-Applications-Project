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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FormComponent,
    ListComponent,
    CustomerComponent,
    OrderComponent,
    ShopComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    RouterModule.forRoot([
      {path: '', component: HomeComponent},
      {path: 'customer', component: CustomerComponent},
      {path: 'order', component: OrderComponent},
      {path: 'shop', component: ShopComponent},
    ])
  ],
  providers: [PostService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

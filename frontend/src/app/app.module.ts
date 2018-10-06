import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { HttpClientModule, HttpClient } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ServiceAppListComponent } from './service-app-list/service-app-list.component';
import { ServiceAppDetailComponent } from './service-app-detail/service-app-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    ServiceAppListComponent,
    ServiceAppDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

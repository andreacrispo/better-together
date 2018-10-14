import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HttpClient } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ServiceAppListComponent } from './service-app-list/service-app-list.component';
import { ServiceAppDetailComponent } from './service-app-detail/service-app-detail.component';
import { ParticipantModalComponent } from './participant-modal/participant-modal.component';
import { ServiceModalComponent } from './service-modal/service-modal.component';
import { MonthSelectorComponent } from './month-selector/month-selector.component';
import { PaidStatusPipe } from './paid-status.pipe';
import { MonthYearPickerComponent } from './month-year-picker/month-year-picker.component';
import { MonthNamePipe } from './month-name.pipe';
// NGX-BOOTSTRAP
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons'
// Font Awesome
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
// Ngx- Toastr
import { ToastrModule } from 'ngx-toastr';



@NgModule({
  declarations: [
    AppComponent,
    ServiceAppListComponent,
    ServiceAppDetailComponent,    
    ParticipantModalComponent,
    MonthYearPickerComponent,
    MonthNamePipe,
    ServiceModalComponent,
    MonthSelectorComponent,
    PaidStatusPipe
  ],
  entryComponents: [ ParticipantModalComponent, ServiceModalComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ModalModule.forRoot(),
    BsDropdownModule.forRoot(),
    ButtonsModule.forRoot(),
    FontAwesomeModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      progressBar: true,
      progressAnimation: 'increasing'
    })
  ],
  providers: [],
  bootstrap: [AppComponent] 
})
export class AppModule { }

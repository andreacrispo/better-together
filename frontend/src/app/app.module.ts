import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ServiceAppListComponent } from './service-app/service-app-list/service-app-list.component';
import { ServiceAppDetailComponent } from './service-app/service-app-detail/service-app-detail.component';
import { ServiceAppModalComponent } from './service-app/service-app-modal/service-app-modal.component';
import { ParticipantModalComponent } from './participant/participant-modal/participant-modal.component';
import { MonthSelectorComponent } from './shared/components/month-selector/month-selector.component';
import { MonthYearPickerComponent } from './shared/components/month-year-picker/month-year-picker.component';
import { PaidStatusPipe } from './shared/pipes/paid-status.pipe';
import { MonthNamePipe } from './shared/pipes/month-name.pipe';
// NGX-BOOTSTRAP
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
// Font Awesome
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
// Ngx- Toastr
import { ToastrModule } from 'ngx-toastr';
import { LoginPageComponent } from './login-page/login-page.component';
import { AuthGuard } from './shared/auth/auth.guard';
import { Interceptor } from './shared/auth/interceptor';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { SignupPageComponent } from './signup-page/signup-page.component';



@NgModule({
  declarations: [
    AppComponent,
    ServiceAppListComponent,
    ServiceAppDetailComponent,
    ParticipantModalComponent,
    MonthYearPickerComponent,
    MonthNamePipe,
    ServiceAppModalComponent,
    MonthSelectorComponent,
    PaidStatusPipe,
    LoginPageComponent,
    NavbarComponent,
    SignupPageComponent
  ],
  entryComponents: [ ParticipantModalComponent, ServiceAppModalComponent],
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
  providers: [
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

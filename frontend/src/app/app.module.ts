import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';


import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { ServiceAppModule } from './service-app/service-app.module';
import { LoginSignupModule } from './login-signup/login-signup.module';
import { ParticipantModule } from './participant/participant.module';


@NgModule({
  imports: [
    BrowserAnimationsModule,
    BrowserModule,

    CoreModule,
    SharedModule,

    // features module
    LoginSignupModule,
    ServiceAppModule,
    ParticipantModule,

    AppRoutingModule
  ],
  declarations: [AppComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}



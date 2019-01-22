import { NgModule } from '@angular/core';
import { LoginPageComponent } from './login-page.component';
import { SignupPageComponent } from './signup-page.component';
import { SharedModule } from '../shared/shared.module';



@NgModule({
    imports: [
       SharedModule
    ],
    declarations: [
        LoginPageComponent,
        SignupPageComponent
    ]
})
export class LoginSignupModule {}

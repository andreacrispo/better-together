import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ServiceAppListComponent } from './service-app/service-app-list/service-app-list.component';
import { ServiceAppDetailComponent} from './service-app/service-app-detail/service-app-detail.component';

import { AuthGuard } from './core/auth/auth.guard';
import { LoginPageComponent } from './login-signup/login-page.component';
import { SignupPageComponent } from './login-signup/signup-page.component';

const routes: Routes = [
    { path: 'login',   component: LoginPageComponent},
    { path: 'signup',  component: SignupPageComponent},
    {
        path: '',
        canActivate: [AuthGuard],
        children: [
            { path: 'services',     component: ServiceAppListComponent},
            { path: 'services/:id', component: ServiceAppDetailComponent},
        ]
    },
    {
      path: '',
      redirectTo: '/services',
      pathMatch: 'full'
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(
            routes,
            { enableTracing: false, useHash: true }
        )
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {}

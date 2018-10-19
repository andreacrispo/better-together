import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { ServiceAppListComponent } from "./service-app/service-app-list/service-app-list.component";
import { ServiceAppDetailComponent} from "./service-app/service-app-detail/service-app-detail.component";
import { LoginPageComponent } from "./login-page/login-page.component";
import { AuthGuard } from "./shared/auth/auth.guard";
import { SignupPageComponent } from "./signup-page/signup-page.component";

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
]

@NgModule({
    imports: [
        RouterModule.forRoot(
            routes,
            { enableTracing: false }
        )
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {}

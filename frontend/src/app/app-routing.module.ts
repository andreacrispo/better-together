import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from "./app.component";
import { ServiceAppListComponent } from "./service-app-list/service-app-list.component";
import { ServiceAppDetailComponent} from "./service-app-detail/service-app-detail.component";

const routes: Routes = [
    { path: 'services',   component: ServiceAppListComponent},
    { path: 'services/:id',   component: ServiceAppDetailComponent},
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

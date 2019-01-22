import { NgModule } from '@angular/core';
import { ServiceAppListComponent } from './service-app-list/service-app-list.component';
import { ServiceAppDetailComponent } from './service-app-detail/service-app-detail.component';
import { ServiceAppModalComponent } from './service-app-modal/service-app-modal.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
    imports: [
        SharedModule
    ],
    declarations: [
        ServiceAppListComponent,
        ServiceAppDetailComponent,
        ServiceAppModalComponent,
    ],
    entryComponents: [ ServiceAppModalComponent],
})
export class ServiceAppModule {}

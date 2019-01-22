import { NgModule } from '@angular/core';
import { ParticipantModalComponent } from './participant-modal/participant-modal.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
    imports: [
        SharedModule
    ],
    declarations: [
        ParticipantModalComponent
    ],
    entryComponents: [ ParticipantModalComponent ]
})
export class ParticipantModule {}

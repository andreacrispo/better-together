import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { Participant, ServiceParticipant, ActionType } from '../../shared/domain/domain'
import { ServiceAppService } from '../../shared/services/serviceApp.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-participant-modal',
  templateUrl: './participant-modal.component.html',
  styleUrls: ['./participant-modal.component.css']
})
export class ParticipantModalComponent implements OnInit {

  service: ServiceParticipant;
  participant: Participant;
  actionType: ActionType;
  model: any;

  
  constructor(public bsModalRef: BsModalRef,
              private toastr: ToastrService,
              private serviceAppService: ServiceAppService) { }

  ngOnInit(): void {
    this.model = this.participant;
  }

  onSubmit() {
 
    if(this.actionType == ActionType.CREATE){
      this.serviceAppService.addParticipant(this.service.serviceId, this.participant)
          .subscribe(
            resp => { this.toastr.success("participant created"); this.bsModalRef.hide() },
            err =>  this.toastr.error("Error during create participant", "Error")
          )
    }else if( this.actionType == ActionType.UPDATE) {
      this.serviceAppService.editParticipant(this.service.serviceId, this.participant)
          .subscribe(
            resp => { this.toastr.success("Participant edited"); this.bsModalRef.hide() },
            err =>  this.toastr.error("Error during edit Participant", "Error")
          )
    }
  }

}

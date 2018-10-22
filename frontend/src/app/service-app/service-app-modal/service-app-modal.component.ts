import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { ServiceParticipant, ActionType } from '../../shared/domain/domain';
import { ServiceAppService } from '../../shared/services/serviceApp.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-service-modal',
  templateUrl: './service-modal.component.html',
  styleUrls: ['./service-modal.component.css']
})
export class ServiceAppModalComponent implements OnInit {

  serviceApp: ServiceParticipant;
  actionType: ActionType;
  model: any;

  constructor(public bsModalRef: BsModalRef,
              private toastr: ToastrService,
              private serviceAppService: ServiceAppService) { }


  ngOnInit(): void {
    console.log('Init ' + this.serviceApp);
    this.model = this.serviceApp;
  }

  onSubmit() {
      if (this.actionType === ActionType.CREATE) {
        this.serviceAppService.create(this.serviceApp)
            .subscribe(
              resp => { this.toastr.success('Service created'); this.bsModalRef.hide(); },
              err =>  this.toastr.error('Error during create service', 'Error')
            );
      } else if ( this.actionType === ActionType.UPDATE) {
        this.serviceAppService.update(this.serviceApp)
            .subscribe(
                resp => { this.toastr.success('Service edited'); this.bsModalRef.hide(); },
                err =>  this.toastr.error('Error during edit service', 'Error')
            );
      }



  }


}

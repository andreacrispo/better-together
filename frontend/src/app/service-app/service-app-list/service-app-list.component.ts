import { Component, OnInit } from '@angular/core';
import { ServiceAppService } from '../../shared/services/serviceApp.service';
import { ServiceParticipant, ActionType } from "../../shared/domain/domain";

import { faEdit, faTrashAlt, faPlus } from '@fortawesome/free-solid-svg-icons';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { ServiceAppModalComponent } from '../service-app-modal/service-app-modal.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-service-app-list',
  templateUrl: './service-app-list.component.html',
  styleUrls: ['./service-app-list.component.css']
})
export class ServiceAppListComponent implements OnInit {

  editIcon   = faEdit;
  addIcon    = faPlus;
  deleteIcon = faTrashAlt;

  serviceAppList: Array<ServiceParticipant>; 

  bsModalRef: BsModalRef;

  constructor(private serviceAppService: ServiceAppService,
              private toastr: ToastrService,
              private modalService: BsModalService) {  }

  ngOnInit() {
      this.getServicesList();
    
  }

  getServicesList(){
    this.serviceAppService.getAll()
        .subscribe( list => this.serviceAppList = list);
  } 


  addService() {   
     const initialState = { 
        serviceApp: new ServiceParticipant(),
        actionType: ActionType.CREATE
    };
    this.bsModalRef =  this.modalService.show(ServiceAppModalComponent, {initialState})
    this.modalService.onHide.subscribe((reason: string) => this.getServicesList());
  }

  editService(serviceApp) {
    console.log("EDIT service:" + serviceApp)
    const initialState = { 
        serviceApp: serviceApp,
        actionType: ActionType.UPDATE
    };
    this.modalService.show(ServiceAppModalComponent, {initialState});
    this.modalService.onHide.subscribe((reason: string) => this.getServicesList());
  }

  deleteService(serviceId){
    console.log("DELETE service:" + serviceId)
    this.serviceAppService.delete(serviceId)
      .subscribe(
        resp => {
          this.toastr.success("Service removed")
          this.getServicesList() // Re-make a request or delete object from array localy? 
        },
        err  => this.toastr.error("Error during remove service", "Error")
      );
  }

}

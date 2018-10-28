import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Participant, ServiceParticipant } from '../../shared/domain/domain';
import { ServiceAppService } from '../../shared/services/serviceApp.service';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { ParticipantModalComponent } from '../../participant/participant-modal/participant-modal.component';
import { faEdit, faTrashAlt, faPlus } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-service-app-detail',
  templateUrl: './service-app-detail.component.html',
  styleUrls: ['./service-app-detail.component.css']
})
export class ServiceAppDetailComponent implements OnInit {
  deleteIcon = faTrashAlt;
  editIcon   = faEdit;
  addIcon    = faPlus;

  id: any;
  month: number ;
  year: number ;
  serviceApp: ServiceParticipant;
  participantsPaid: number;

  paidFilter: boolean = undefined;
  bsModalRef: BsModalRef;

  constructor(private route: ActivatedRoute,
              private modalService: BsModalService,
              private toastr: ToastrService,
              private serviceAppService: ServiceAppService) {  }


  ngOnInit() {
    const today = new Date();
    this.year =  +this.route.snapshot.queryParamMap.get('year')  || today.getFullYear();
    this.month = +this.route.snapshot.queryParamMap.get('month') || today.getMonth() + 1;
    this.route.params.subscribe(params => {
       this.id = params['id'];
       this.getServiceDetail(this.id, this.month, this.year);
    });
  }

  private getServiceDetail(serviceId, month?, year?) {
    this.serviceAppService.get(serviceId, month, year)
        .subscribe(
          service => {
            this.serviceApp = service;
            if (this.serviceApp.participants.length > 0) {
                this.participantsPaid = this.serviceApp.participants.map(p => p.pricePaid).reduce( (acc, pricePaid) => acc + pricePaid );
            }
          },
          error => this.toastr.error(error, 'Error')
        );
  }

  addParticipant() {
    const p = new Participant();
    p.yearPaid = this.year;
    p.monthPaid = this.month;
    const initialState = { service: this.serviceApp,  participant: p, actionType: 'CREATE'  };
    this.bsModalRef = this.modalService.show(ParticipantModalComponent, {initialState});
    this.modalService.onHide.subscribe((reason: string) => this.getServiceDetail(this.id, this.month, this.year));
  }

  editParticipant(participant?: Participant) {
    const initialState = { service: this.serviceApp,  participant: participant, actionType: 'UPDATE'  };
    this.bsModalRef = this.modalService.show(ParticipantModalComponent, {initialState});
    this.modalService.onHide.subscribe((reason: string) => this.getServiceDetail(this.id, this.month, this.year));
  }

  removeParticipant(participant: Participant) {
    const p = {...participant};
    p.yearPaid = this.year;
    p.monthPaid = this.month;
    this.serviceAppService.removeParticipant(this.serviceApp.serviceId, p)
        .subscribe(
             resp => {
                  this.toastr.success(`${participant.name} removed`);
                  this.getServiceDetail(this.id, this.month, this.year);
             },
             err  => this.toastr.error('Error during remove item', 'Error')
        );
  }

  changePaidStatus(participant: Participant) {
    if (participant.hasPaid) {
      participant.hasPaid   = false;
      participant.pricePaid = null;
    } else  {
      participant.hasPaid = true;
      participant.pricePaid = this.calculatePricePaid();
    }

    this.serviceAppService.editParticipant(this.serviceApp.serviceId, participant)
        .subscribe(resp => this.getServiceDetail(this.id, this.month, this.year));
  }

  copyParticipants() {
    this.serviceAppService.copyParticipants(this.id, this.month, this.year)
        .subscribe(resp => {
            this.toastr.success('Participants copied');
            this.getServiceDetail(this.id, this.month, this.year);
        });
  }

  changeYearMonthEvent(e) {
    this.year = e.year;
    this.month = e.month;
    this.getServiceDetail(this.id, this.month, this.year);
  }


  private calculatePricePaid() {
      return this.serviceApp.participantNumber !== undefined
         ? this.serviceApp.monthlyPrice / this.serviceApp.participantNumber
         : this.serviceApp.monthlyPrice / this.serviceApp.participants.length;
  }

}



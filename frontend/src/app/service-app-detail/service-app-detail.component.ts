import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ServiceParticipant } from "../domain";

@Component({
  selector: 'app-service-app-detail',
  templateUrl: './service-app-detail.component.html',
  styleUrls: ['./service-app-detail.component.css']
})
export class ServiceAppDetailComponent implements OnInit {

  id: any; 
  month: any;
  year: any;
  serviceApp: ServiceParticipant;

  constructor(private route: ActivatedRoute,
              private http: HttpClient) {  }


  ngOnInit() {
    this.route.params.subscribe(params => {
      console.log(params);
       this.id = params['id'];
       this.getServiceDetail(this.id);
    });
  }

  private getServiceDetail(serviceId, month?, year?) {
    this.http.get<ServiceParticipant>(`http://localhost:8080/api/services/${serviceId}`)
        .subscribe( service => this.serviceApp = service);
  }


}

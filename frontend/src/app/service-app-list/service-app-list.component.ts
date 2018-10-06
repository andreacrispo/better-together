import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ServiceParticipant } from "../domain";


@Component({
  selector: 'app-service-app-list',
  templateUrl: './service-app-list.component.html',
  styleUrls: ['./service-app-list.component.css']
})
export class ServiceAppListComponent implements OnInit {

  serviceAppList: Array<ServiceParticipant>; 

  constructor(private http: HttpClient) { }

  ngOnInit() {
      this.getServicesList();
    
  }

  getServicesList(){
    this.http.get<Array<ServiceParticipant>>("http://localhost:8080/api/services/")
        .subscribe( list => this.serviceAppList = list);
  } 

}

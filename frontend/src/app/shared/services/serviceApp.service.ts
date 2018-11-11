import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceParticipant, Participant } from '../domain/domain';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class ServiceAppService {

    public SERVICE_API =  environment.API_BASE_URL +  '/api/services';

    constructor(private http: HttpClient) {
    }

    getAll(): Observable<Array<ServiceParticipant>> {
        return this.http.get<Array<ServiceParticipant>>(this.SERVICE_API);
    }

    get(serviceId: string, month?, year?): Observable<ServiceParticipant> {
      const request = `${this.SERVICE_API}/${serviceId}?month=${month}&year=${year}`;
      return  this.http.get<ServiceParticipant>(request);
    }

    create(serviceApp: ServiceParticipant) {
        return this.http.post(this.SERVICE_API, serviceApp);
    }

    update(serviceApp: ServiceParticipant) {
        const request = `${this.SERVICE_API}/${serviceApp.serviceId}`;
        return this.http.put(request, serviceApp);
    }

    delete(serviceId: string) {
        const request = `${this.SERVICE_API}/${serviceId}`;
        return this.http.delete(request);
    }

    addParticipant(serviceId: number | string, participant: Participant) {
        const request = `${this.SERVICE_API}/${serviceId}/participants`;
        return this.http.post(request, participant);
    }

    editParticipant(serviceId: number | string, participant: Participant) {
        const request = `${this.SERVICE_API}/${serviceId}/participants/${participant.id}/update`;
        return this.http.post(request, participant);
    }


    removeParticipant(serviceId: number | string, participant: Participant) {
        const request = `${this.SERVICE_API}/${serviceId}/participants/${participant.id}/delete`;
        return this.http.post(request, participant);
    }


    copyParticipants(serviceId: number, month: number, year: number) {
        const request = `${this.SERVICE_API}/${serviceId}/participants/copy?month=${month}&year=${year}`;
        return this.http.post(request, {});
    }

}

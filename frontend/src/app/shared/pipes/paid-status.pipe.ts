import { Pipe, PipeTransform } from '@angular/core';
import { Participant } from '../domain/domain';

@Pipe({
  name: 'paidStatus'
})
export class PaidStatusPipe implements PipeTransform {

  transform(participants: Participant[], filter: boolean): any {
      if (filter === undefined) {
        return participants;
      }
      return participants.filter( p => p.hasPaid === filter);
  }

}

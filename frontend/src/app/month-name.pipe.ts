import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'monthName'
})
export class MonthNamePipe implements PipeTransform {

  months = {
    "IT":   ["", "Gennaio", "Febbrario", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"],
    "EN":   ["", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
  }

  transform(index: any, language    ?: any): any {
    return this.months[language||"EN"][+index];
  }

}

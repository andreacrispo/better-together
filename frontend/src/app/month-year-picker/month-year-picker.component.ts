import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from "@angular/core";

import { faCalendarAlt, faChevronLeft, faChevronRight } from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-month-year-picker',
  templateUrl: './month-year-picker.component.html',
  styleUrls: ['./month-year-picker.component.css']
})
export class MonthYearPickerComponent implements OnInit {
  
  calendarIcon = faCalendarAlt; leftIcon = faChevronLeft; rightIcon = faChevronRight;

  year;
  months: string[];
  isyearSelected: boolean;
  incr: number;

  @Output() yearMonthSelected = new EventEmitter();

  constructor() { }

  ngOnInit() {
    this.year = new Date().getFullYear();
    this.isyearSelected = false;
    this.months = ["Jan.", "Feb. ", "Mar.", "Apr.", "May", "June", "July", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."];
    this.incr = this.getIncr(this.year);
  }

  getIncr(year: number): number {
    return (year-year%10)-1;
  }

  showYear($event:any, show:boolean) {
    $event.stopPropagation();
    this.isyearSelected = !this.isyearSelected;
  }

  changeYear(event, incr){
    event.stopPropagation(); 
    let year= this.isyearSelected ? this.year+10*incr : this.year+incr;
    console.log(year);
    this.year=year;
    this.incr=this.getIncr(year);
  }

  selectYearMonth(event, index) {
    if (this.isyearSelected) {
       event.stopPropagation();
       this.year= index + this.incr;
       this.isyearSelected = false;
       this.incr = this.getIncr(this.year);
    }else {
      this.yearMonthSelected.emit({year: this.year, month: index+1});
    }
  }

}

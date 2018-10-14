import { Component, OnInit, Input, Output, EventEmitter, HostListener } from '@angular/core';
import {faChevronLeft, faChevronRight }  from  "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-month-selector',
  templateUrl: './month-selector.component.html',
  styleUrls: ['./month-selector.component.css']
})
export class MonthSelectorComponent implements OnInit {

  @Input() month: number;
  @Input() year: number;

  @Output() onChangeMonth = new EventEmitter();

  leftIcon  = faChevronLeft;
  rightIcon = faChevronRight;

  constructor() { }

  ngOnInit() {
  }


  minusMonth(){
    if(this.month - 1 <= 0) {
      this.month = 12;
      this.year -= 1;
    }else{
      this.month -= 1;
    }
    this.changeYearMonthEvent({ year: this.year, month: this.month});
  }

  plusMonth() {
    if(this.month + 1 >= 13){
      this.month = 1;
      this.year += 1;
    }else{
      this.month += 1;
    }
    this.changeYearMonthEvent({ year: this.year, month: this.month});
  }


  changeYearMonthEvent(e) {
    this.onChangeMonth.emit(e);
  }

  @HostListener('document:keydown', ['$event'])
  public handleKeyboardEvent(event: KeyboardEvent): void {
    event.stopPropagation();
    if(event.key == "ArrowLeft"){
      this.minusMonth();
    }else if(event.key == "ArrowRight"){
      this.plusMonth();
    }
  }

}

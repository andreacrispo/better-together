import { NgModule  } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { library } from '@fortawesome/fontawesome-svg-core';

import { fas } from '@fortawesome/free-solid-svg-icons';

import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ToastrModule } from 'ngx-toastr';
import { MonthNamePipe } from './pipes/month-name.pipe';
import { PaidStatusPipe } from './pipes/paid-status.pipe';
import { RouterModule } from '@angular/router';
import { MonthSelectorComponent } from './components/month-selector/month-selector.component';
import { MonthYearPickerComponent } from './components/month-year-picker/month-year-picker.component';
import { NavbarComponent } from './components/navbar/navbar.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        FontAwesomeModule,
        RouterModule,

        ModalModule.forRoot(),
        BsDropdownModule.forRoot(),
        ButtonsModule.forRoot(),

        ToastrModule.forRoot({
          timeOut: 3000,
          progressBar: true,
          progressAnimation: 'increasing'
        }),

    ],
    declarations: [
        NavbarComponent,
        MonthSelectorComponent,
        MonthYearPickerComponent,
        MonthNamePipe,
        PaidStatusPipe
    ],
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        FontAwesomeModule,
        RouterModule,

        ModalModule,
        BsDropdownModule,
        ButtonsModule,

        ToastrModule,
        NavbarComponent,
        MonthSelectorComponent,
        MonthYearPickerComponent,
        MonthNamePipe,
        PaidStatusPipe
    ],

})
export class SharedModule {

    constructor() {
        library.add(fas);
    }
}

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceAppModalComponent } from './service-app-modal.component';

describe('ServiceModalComponent', () => {
  let component: ServiceAppModalComponent;
  let fixture: ComponentFixture<ServiceAppModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceAppModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceAppModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

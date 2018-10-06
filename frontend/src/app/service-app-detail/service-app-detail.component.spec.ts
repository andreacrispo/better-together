import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceAppDetailComponent } from './service-app-detail.component';

describe('ServiceAppDetailComponent', () => {
  let component: ServiceAppDetailComponent;
  let fixture: ComponentFixture<ServiceAppDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceAppDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceAppDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

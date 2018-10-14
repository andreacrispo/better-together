import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceAppListComponent } from './service-app-list.component';

describe('ServiceAppListComponent', () => {
  let component: ServiceAppListComponent;
  let fixture: ComponentFixture<ServiceAppListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceAppListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceAppListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralUserComponent } from './general-user.component';

describe('GeneralUserComponent', () => {
  let component: GeneralUserComponent;
  let fixture: ComponentFixture<GeneralUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GeneralUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeneralUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

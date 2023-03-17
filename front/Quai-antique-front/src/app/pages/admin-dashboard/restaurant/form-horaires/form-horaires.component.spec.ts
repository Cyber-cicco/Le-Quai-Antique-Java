import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormHorairesComponent } from './form-horaires.component';

describe('FormHorairesComponent', () => {
  let component: FormHorairesComponent;
  let fixture: ComponentFixture<FormHorairesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormHorairesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormHorairesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

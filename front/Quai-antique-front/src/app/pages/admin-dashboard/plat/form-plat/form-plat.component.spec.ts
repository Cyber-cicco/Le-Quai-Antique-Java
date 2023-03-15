import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPlatComponent } from './form-plat.component';

describe('FormPlatComponent', () => {
  let component: FormPlatComponent;
  let fixture: ComponentFixture<FormPlatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormPlatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormPlatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

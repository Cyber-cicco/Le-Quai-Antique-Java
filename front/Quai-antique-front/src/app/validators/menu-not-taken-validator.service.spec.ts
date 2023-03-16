import { TestBed } from '@angular/core/testing';

import { MenuNotTakenValidatorService } from './menu-not-taken-validator.service';

describe('MenuNotTakenValidatorService', () => {
  let service: MenuNotTakenValidatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenuNotTakenValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

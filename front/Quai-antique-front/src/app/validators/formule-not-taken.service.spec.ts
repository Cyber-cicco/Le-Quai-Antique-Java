import { TestBed } from '@angular/core/testing';

import { FormuleNotTakenService } from './formule-not-taken.service';

describe('FormuleNotTakenService', () => {
  let service: FormuleNotTakenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormuleNotTakenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

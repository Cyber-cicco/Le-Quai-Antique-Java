import { TestBed } from '@angular/core/testing';

import { PlatNotTakenService } from './plat-not-taken.service';

describe('PlatNotTakenService', () => {
  let service: PlatNotTakenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlatNotTakenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

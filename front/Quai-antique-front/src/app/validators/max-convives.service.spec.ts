import { TestBed } from '@angular/core/testing';

import { MaxConvivesService } from './max-convives.service';

describe('MaxConvivesService', () => {
  let service: MaxConvivesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MaxConvivesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

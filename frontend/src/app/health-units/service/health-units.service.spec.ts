import { TestBed } from '@angular/core/testing';

import { HealthUnitsService } from './health-units.service';

describe('HealthUnitsService', () => {
  let service: HealthUnitsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HealthUnitsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

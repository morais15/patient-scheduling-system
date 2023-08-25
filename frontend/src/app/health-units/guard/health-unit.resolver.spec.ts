import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { healthUnitResolver } from './health-unit.resolver';

describe('healthUnitResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => healthUnitResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});

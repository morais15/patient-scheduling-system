import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { medicResolver } from './medic.resolver';

describe('medicResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => medicResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});

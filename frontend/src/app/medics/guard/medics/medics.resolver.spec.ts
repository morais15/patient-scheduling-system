import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { medicsResolver } from './medics.resolver';

describe('medicsResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => medicsResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});

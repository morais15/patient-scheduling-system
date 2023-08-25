import { ResolveFn } from '@angular/router';
import { of } from 'rxjs';
import { HealthUnit } from '../domain/health-unit';
import { HealthUnitsService } from '../service/health-units.service';
import { inject } from '@angular/core';

export const healthUnitResolver: ResolveFn<HealthUnit> = (route, state, service: HealthUnitsService = inject(HealthUnitsService)) => {
  if (route.params?.['id']) {
    return service.findById(route.params['id'])
  }

  return of({ id: 0, name: '', address: '' });
};

import { ResolveFn } from '@angular/router';
import { MedicsService } from '../../service/medics.service';
import { inject } from '@angular/core';
import { Medic } from '../../domain/medic';
import { of } from 'rxjs';

export const medicResolver: ResolveFn<Medic> = (route, state, service: MedicsService = inject(MedicsService)) => {
  if (route.params?.['id']) {
    console.log(route.params)
    return service.findById(route.params['id'])
  }

  return of({ id: 0, name: '', specialty: '', healthUnit: null });
};

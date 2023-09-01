import { ResolveFn } from '@angular/router';
import { Medic } from '../../domain/medic';
import { MedicsService } from '../../service/medics.service';
import { inject } from '@angular/core';
import { catchError, of } from 'rxjs';

export const medicsResolver: ResolveFn<Medic[]> = (route, state, service: MedicsService = inject(MedicsService)) => {
  const errorMsg: string = 'Error on get medics'

  if (route.params?.['id']) {
    return service.findByHealthUnitId(route.params['id'])
      .pipe(
        catchError(() => {
          service.onError(errorMsg)
          return of([])
        })
      );
  }

  return service.findAll()
    .pipe(
      catchError(() => {
        service.onError(errorMsg)
        return of([])
      })
    );
};

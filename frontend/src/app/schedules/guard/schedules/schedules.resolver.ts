import { ResolveFn } from '@angular/router';
import { SchedulesService } from '../../service/schedules.service';
import { Schedule } from '../../domain/schedule';
import { inject } from '@angular/core';
import { catchError, of } from 'rxjs';

export const schedulesResolver: ResolveFn<Schedule[]> = (route, state, service: SchedulesService = inject(SchedulesService)) => {
  const errorMsg: string = 'Error on get schedules'

  if (route.params?.['id']) {
    return service.findByMedic(route.params['id'])
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

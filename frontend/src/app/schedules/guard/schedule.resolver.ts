import { ResolveFn } from '@angular/router';
import { SchedulesService } from '../service/schedules.service';
import { inject } from '@angular/core';
import { of } from 'rxjs';
import { Schedule } from '../domain/Schedule';

export const scheduleResolver: ResolveFn<Schedule> = (route, state, service: SchedulesService = inject(SchedulesService)) => {
  if (route.params?.['id']) {
    return service.findById(route.params['id'])
  }

  return of({ id: 0, dateTime: '', status: '' });
};

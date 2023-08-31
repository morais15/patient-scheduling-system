import { Component } from '@angular/core';
import { Schedule } from '../../domain/schedule';
import { Observable, catchError, of } from 'rxjs';
import { SchedulesService } from '../../service/schedules.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss']
})
export class SchedulesComponent {
  schedules$: Observable<Schedule[]>;

  constructor(
    private schedulesService: SchedulesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.schedules$ = this.refresh();
  }

  private refresh(): Observable<Schedule[]> {
    return this.schedulesService
      .findAll()
      .pipe(
        catchError(() => {
          this.schedulesService.onError('Error on get schedules.')
          return of([])
        })
      )
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.activatedRoute })
  }

  onEdit(schedule: Schedule) {
    this.router.navigate(['edit', schedule.id], { relativeTo: this.activatedRoute })
  }

  onDelete(schedule: Schedule) {
    this.schedulesService.confirm("Do you want to delete the item?")
      .subscribe((confirm: Boolean) => {
        if (confirm) {
          this.delete(schedule)
        }
      })
  }

  private delete(schedule: Schedule) {
    this.schedulesService.delete(schedule.id)
      .subscribe({
        next: () => {
          this.schedulesService.onSuccess("Success on delete")
          this.schedules$ = this.refresh()
        },
        error: () => this.schedulesService.onError('Error on delete schedule.')
      })
  }
}

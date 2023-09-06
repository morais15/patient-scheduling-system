import { Component } from '@angular/core';
import { Schedule } from '../../domain/schedule';
import { SchedulesService } from '../../service/schedules.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss']
})
export class SchedulesComponent {
  schedules: Schedule[];

  constructor(
    private schedulesService: SchedulesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.schedules = this.activatedRoute.snapshot.data['schedules'];
  }

  onAdd() {
    this.router.navigate(['schedules', 'new'])
  }

  onEdit(schedule: Schedule) {
    this.router.navigate(['schedules', 'edit', schedule.id])
  }

  onPatient(schedule: Schedule){
    this.router.navigate(['schedules', 'patient', schedule.id])
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
          this.schedulesService.onSuccess("Success on delete");
          window.location.reload();
        },
        error: () => this.schedulesService.onError('Error on delete schedule.')
      })
  }
}

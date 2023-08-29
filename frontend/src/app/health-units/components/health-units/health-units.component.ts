import { Component } from '@angular/core';
import { HealthUnit } from '../../domain/health-unit';
import { HealthUnitsService } from '../../service/health-units.service';
import { Observable, catchError, of } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-health-units',
  templateUrl: './health-units.component.html',
  styleUrls: ['./health-units.component.scss']
})
export class HealthUnitsComponent {
  healthUnits$: Observable<HealthUnit[]>;

  constructor(
    private healthUnitsService: HealthUnitsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.healthUnits$ = this.refresh();
  }

  private refresh(): Observable<HealthUnit[]> {
    return this.healthUnitsService
      .findAll()
      .pipe(
        catchError(() => {
          this.healthUnitsService.onError('Error on get health units.')
          return of([])
        })
      )
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.activatedRoute })
  }

  onEdit(healthUnit: HealthUnit) {
    this.router.navigate(['edit', healthUnit.id], { relativeTo: this.activatedRoute })
  }

  onDelete(healthUnit: HealthUnit) {
    this.healthUnitsService.confirm("Do you want to delete the item?")
      .subscribe((confirm: Boolean) => {
        if (confirm) {
          this.delete(healthUnit)
        }
      })
  }

  private delete(healthUnit: HealthUnit) {
    this.healthUnitsService.delete(healthUnit.id)
      .subscribe({
        next: () => {
          this.healthUnitsService.onSuccess("Success on delete")
          this.healthUnits$ = this.refresh()
        },
        error: () => this.healthUnitsService.onError('Error on delete health unit.')
      })
  }
}

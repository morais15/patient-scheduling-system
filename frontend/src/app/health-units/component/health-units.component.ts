import { Component } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HealthUnitsService } from '../service/health-units.service';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-health-units',
  templateUrl: './health-units.component.html',
  styleUrls: ['./health-units.component.scss']
})
export class HealthUnitsComponent {
  healthUnits$: Observable<HealthUnit[]>;
  displayedColumns = ['name', 'address']

  constructor(private healthUnitsService: HealthUnitsService, private dialog: MatDialog) {
    this.healthUnits$ = this.healthUnitsService
      .listAll()
      .pipe(
        catchError(err => {
          this.onError('Error on get health units.')
          return of([])
        })
      )
  }

  onError(errorMsg: String) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    })
  }
}

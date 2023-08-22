import { Component } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HealthUnitsService } from '../service/health-units.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-health-units',
  templateUrl: './health-units.component.html',
  styleUrls: ['./health-units.component.scss']
})
export class HealthUnitsComponent {
  healthUnits$: Observable<HealthUnit[]>;
  displayedColumns = ['name', 'address']

  constructor(private healthUnitsService: HealthUnitsService){
    this.healthUnits$ = this.healthUnitsService.listAll();
  }
}

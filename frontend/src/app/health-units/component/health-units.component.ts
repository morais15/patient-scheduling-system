import { Component } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HealthUnitsService } from '../service/health-units.service';

@Component({
  selector: 'app-health-units',
  templateUrl: './health-units.component.html',
  styleUrls: ['./health-units.component.scss']
})
export class HealthUnitsComponent {
  constructor(private healthUnitsService: HealthUnitsService){ }

  healthUnits: HealthUnit[] = this.healthUnitsService.list();
  displayedColumns = ['name', 'address']
}

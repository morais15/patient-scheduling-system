import { Component } from '@angular/core';
import { HealthUnit } from './domain/health-unit';

@Component({
  selector: 'app-health-units',
  templateUrl: './health-units.component.html',
  styleUrls: ['./health-units.component.scss']
})
export class HealthUnitsComponent {
  healthUnits: HealthUnit[] = [];
  displayedColumns = ['name', 'address']
}

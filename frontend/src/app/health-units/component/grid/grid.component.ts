import { Component, EventEmitter, Input, Output } from '@angular/core';
import { HealthUnit } from '../../domain/health-unit';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent {
  @Input() healthUnits: HealthUnit[] = [];
  @Output() add = new EventEmitter();

  readonly displayedColumns = ['id', 'name', 'address', 'actions']

  onAdd() {
    this.add.emit(true)
  }
}

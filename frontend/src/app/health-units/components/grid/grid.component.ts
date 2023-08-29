import { Component, EventEmitter, Input, Output } from '@angular/core';
import { HealthUnit } from '../../domain/health-unit';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent {
  @Input() healthUnits: HealthUnit[] = [];
  @Output() add = new EventEmitter();
  @Output() edit = new EventEmitter();
  @Output() delete = new EventEmitter();

  readonly displayedColumns = ['id', 'name', 'address', 'actions']

  onAdd() {
    this.add.emit(true)
  }

  onEdit(healthUnit: HealthUnit) {
    this.edit.emit(healthUnit)
  }

  onDelete(healthUnit: HealthUnit){
    this.delete.emit(healthUnit)
  }
}

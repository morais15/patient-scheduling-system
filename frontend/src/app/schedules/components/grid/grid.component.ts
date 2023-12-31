import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Schedule } from '../../domain/schedule';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent {
  @Input() schedules: Schedule[] = [];
  @Output() add = new EventEmitter();
  @Output() edit = new EventEmitter();
  @Output() delete = new EventEmitter();
  @Output() patient = new EventEmitter();

  readonly displayedColumns = ['id', 'dateTime', 'status', 'patient', 'actions']

  onAdd() {
    this.add.emit(true)
  }

  onEdit(schedule: Schedule) {
    this.edit.emit(schedule)
  }

  onDelete(schedule: Schedule) {
    this.delete.emit(schedule)
  }

  onPatient(schedule: Schedule) {
    this.patient.emit(schedule)
  }
}

import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Medic } from '../../domain/medic';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent {
  @Input() medics: Medic[] = [];
  @Output() add = new EventEmitter();
  @Output() edit = new EventEmitter();
  @Output() delete = new EventEmitter();
  @Output() generate = new EventEmitter();
  @Output() enter = new EventEmitter();

  readonly displayedColumns = ['id', 'name', 'specialty', 'actions']

  onAdd() {
    this.add.emit(true)
  }

  onEdit(medic: Medic) {
    this.edit.emit(medic)
  }

  onDelete(medic: Medic) {
    this.delete.emit(medic)
  }

  onGenerate(medic: Medic) {
    this.generate.emit(medic)
  }

  onEnter(medic: Medic) {
    this.enter.emit(medic)
  }
}

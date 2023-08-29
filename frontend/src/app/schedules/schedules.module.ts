import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SchedulesRoutingModule } from './schedules-routing.module';
import { SchedulesComponent } from './components/schedules/schedules.component';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { GridComponent } from './components/grid/grid.component';
import { FormComponent } from './components/form/form.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    SchedulesComponent,
    GridComponent,
    FormComponent
  ],
  imports: [
    CommonModule,
    SchedulesRoutingModule,
    ReactiveFormsModule,
    AppMaterialModule
  ]
})
export class SchedulesModule { }

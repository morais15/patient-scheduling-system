import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HealthUnitsRoutingModule } from './health-units-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { GridComponent } from './components/grid/grid.component';
import { HealthUnitsComponent } from './components/health-units/health-units.component';
import { FormComponent } from './components/form/form.component';


@NgModule({
  declarations: [
    HealthUnitsComponent,
    FormComponent,
    GridComponent
  ],
  imports: [
    CommonModule,
    HealthUnitsRoutingModule,
    ReactiveFormsModule,
    AppMaterialModule
  ]
})
export class HealthUnitsModule { }

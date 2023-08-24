import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HealthUnitsRoutingModule } from './health-units-routing.module';
import { FormComponent } from './component/form/form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from '../shared/app-material/app-material.module';


@NgModule({
  declarations: [
    FormComponent
  ],
  imports: [
    CommonModule,
    HealthUnitsRoutingModule,
    ReactiveFormsModule,
    AppMaterialModule
  ]
})
export class HealthUnitsModule { }

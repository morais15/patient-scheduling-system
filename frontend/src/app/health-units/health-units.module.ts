import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HealthUnitsRoutingModule } from './health-units-routing.module';
import { FormComponent } from './component/form/form.component';


@NgModule({
  declarations: [
    FormComponent
  ],
  imports: [
    CommonModule,
    HealthUnitsRoutingModule
  ]
})
export class HealthUnitsModule { }

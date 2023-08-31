import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MedicsRoutingModule } from './medics-routing.module';
import { MedicsComponent } from './components/medics/medics.component';
import { GridComponent } from './components/grid/grid.component';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FormComponent } from './components/form/form.component';
import { GenerateComponent } from './components/generate/generate.component';


@NgModule({
  declarations: [
    MedicsComponent,
    GridComponent,
    FormComponent,
    GenerateComponent
  ],
  imports: [
    CommonModule,
    MedicsRoutingModule,
    ReactiveFormsModule,
    AppMaterialModule
  ]
})
export class MedicsModule { }

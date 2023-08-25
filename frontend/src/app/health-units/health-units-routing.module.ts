import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HealthUnitsComponent } from './component/health-units.component';
import { FormComponent } from './component/form/form.component';
import { healthUnitResolver } from './guard/health-unit.resolver';

const routes: Routes = [
  {
    path: '',
    component: HealthUnitsComponent
  },
  {
    path: 'new',
    component: FormComponent,
    resolve: {healthUnit: healthUnitResolver}
  },
  {
    path: 'edit/:id',
    component: FormComponent,
    resolve: {healthUnit: healthUnitResolver}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HealthUnitsRoutingModule { }

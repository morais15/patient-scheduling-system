import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HealthUnitsComponent } from './component/health-units.component';
import { FormComponent } from './component/form/form.component';

const routes: Routes = [
  {
    path: '',
    component: HealthUnitsComponent
  },
  {
    path: 'new',
    component: FormComponent
  },
  {
    path: 'edit/:id',
    component: FormComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HealthUnitsRoutingModule { }

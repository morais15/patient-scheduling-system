import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HealthUnitsComponent } from './health-units.component';

const routes: Routes = [
  {
    path: '',
    component: HealthUnitsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HealthUnitsRoutingModule { }

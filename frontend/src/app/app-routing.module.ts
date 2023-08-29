import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'health-units',
    loadChildren: () => import('./health-units/health-units.module').then(m => m.HealthUnitsModule)
  },
  {
    path: 'medics',
    loadChildren: () => import('./medics/medics.module').then(m => m.MedicsModule)
  },
  {
    path: 'schedules',
    loadChildren: () => import('./schedules/schedules.module').then(m => m.SchedulesModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

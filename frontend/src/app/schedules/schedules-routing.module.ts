import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SchedulesComponent } from './components/schedules/schedules.component';
import { FormComponent } from './components/form/form.component';
import { scheduleResolver } from './guard/schedule.resolver';

const routes: Routes = [
  {
    path: '',
    component: SchedulesComponent
  },
  {
    path: 'new',
    component: FormComponent,
    resolve: { schedule: scheduleResolver }
  },
  {
    path: 'edit/:id',
    component: FormComponent,
    resolve: { schedule: scheduleResolver }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SchedulesRoutingModule { }

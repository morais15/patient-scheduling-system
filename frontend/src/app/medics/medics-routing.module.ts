import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicsComponent } from './components/medics/medics.component';
import { medicResolver } from './guard/medic.resolver';
import { FormComponent } from './components/form/form.component';

const routes: Routes = [
  {
    path: '',
    component: MedicsComponent
  },
  {
    path: 'new',
    component: FormComponent,
    resolve: { medic: medicResolver }
  },
  {
    path: 'edit/:id',
    component: FormComponent,
    resolve: { medic: medicResolver }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicsRoutingModule { }

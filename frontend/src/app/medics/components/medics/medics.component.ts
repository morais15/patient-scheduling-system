import { Component } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { MedicsService } from '../../service/medics.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Medic } from '../../domain/medic';

@Component({
  selector: 'app-medics',
  templateUrl: './medics.component.html',
  styleUrls: ['./medics.component.scss']
})
export class MedicsComponent {
  medics: Medic[];

  constructor(
    private medicsService: MedicsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.medics = this.activatedRoute.snapshot.data['medics'];
  }

  onAdd() {
    this.router.navigate(['medics', 'new'])
  }

  onEdit(medic: Medic) {
    this.router.navigate(['medics', 'edit', medic.id])
  }

  onGenerate(medic: Medic) {
    this.router.navigate(['medics', 'generate', medic.id])
  }

  onEnter(medic: Medic) {
    this.router.navigate(['schedules', 'filter', medic.id])
  }

  onDelete(medic: Medic) {
    this.medicsService.confirm("Do you want to delete the item?")
      .subscribe((confirm: Boolean) => {
        if (confirm) {
          this.delete(medic)
        }
      })
  }

  private delete(medic: Medic) {
    this.medicsService.delete(medic.id)
      .subscribe({
        next: () => {
          this.medicsService.onSuccess("Success on delete")
          //this.medics$ = this.refresh()
        },
        error: () => this.medicsService.onError('Error on delete medic.')
      })
  }
}

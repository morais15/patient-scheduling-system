import { Component } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { MedicsService } from '../../service/medics.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Medic } from '../../domain/Medic';

@Component({
  selector: 'app-medics',
  templateUrl: './medics.component.html',
  styleUrls: ['./medics.component.scss']
})
export class MedicsComponent {
  medics$: Observable<Medic[]>;

  constructor(
    private medicsService: MedicsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.medics$ = this.refresh();
  }

  private refresh(): Observable<Medic[]> {
    return this.medicsService
      .findAll()
      .pipe(
        catchError(() => {
          this.medicsService.onError('Error on get medics.')
          return of([])
        })
      )
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.activatedRoute })
  }

  onEdit(medic: Medic) {
    this.router.navigate(['edit', medic.id], { relativeTo: this.activatedRoute })
  }

  onGenerate(medic: Medic) {
    this.router.navigate(['generate', medic.id], { relativeTo: this.activatedRoute })
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
          this.medics$ = this.refresh()
        },
        error: () => this.medicsService.onError('Error on delete medic.')
      })
  }
}

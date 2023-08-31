import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { Medic } from '../domain/Medic';
import { GenerateSchedules } from '../domain/GenerateSchedules';

@Injectable({
  providedIn: 'root'
})
export class MedicsService {

  private readonly API_URL: String = environment.API_URL;

  constructor(
    private httpClient: HttpClient,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) { }

  findAll(): Observable<Medic[]> {
    return this.httpClient.get<Medic[]>(`${this.API_URL}/medics`)
  }

  findById(id: Number): Observable<Medic> {
    return this.httpClient.get<Medic>(`${this.API_URL}/medics/${id}`)
  }

  save(value: Medic): Observable<Object> {
    if (value.id)
      return this.update(value)
    return this.create(value)
  }

  private create(value: Medic): Observable<Object> {
    return this.httpClient.post(`${this.API_URL}/medics`, value)
  }

  private update(value: Medic): Observable<Object> {
    return this.httpClient.put(`${this.API_URL}/medics/${value.id}`, value)
  }

  delete(id: Number): Observable<Object> {
    return this.httpClient.delete(`${this.API_URL}/medics/${id}`)

  }

  generateSchedules(medicId: Number, value: GenerateSchedules): Observable<Object> {
    return this.httpClient.post(`${this.API_URL}/medics/${medicId}/create-schedules`, value)
  }

  public onError(errorMsg: String) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    })
  }

  public onSuccess(msg: string) {
    this.snackBar.open(msg, "Close", { duration: 5000 })
  }

  public confirm(msg: String): Observable<Boolean> {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: msg,
    });

    return dialogRef.afterClosed();
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Patient } from '../domain/patient';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class PatientsService {

  private readonly API_URL: String = environment.API_URL;

  constructor(
    private httpClient: HttpClient,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) { }

  save(scheduleId: Number, value: Patient): Observable<Object> {
    if (value.id)
      return this.update(value)
    return this.create(scheduleId, value)
  }

  private create(scheduleId: Number, value: Patient): Observable<Object> {
    return this.httpClient.put(`${this.API_URL}/schedules/${scheduleId}/patient`, value)
  }

  private update(value: Patient): Observable<Object> {
    return this.httpClient.put(`${this.API_URL}/patients/${value.id}`, value)
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

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { environment } from 'src/environments/environment.development';
import { Schedule } from '../domain/schedule';
import { Observable } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class SchedulesService {

  private readonly API_URL: String | undefined = environment.API_URL;

  constructor(
    private httpClient: HttpClient,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) { }

  findAll(): Observable<Schedule[]> {
    return this.httpClient.get<Schedule[]>(`${this.API_URL}/schedules`)
  }

  findStatus(): Observable<String[]> {
    return this.httpClient.get<String[]>(`${this.API_URL}/schedules/status`)
  }

  findById(id: Number): Observable<Schedule> {
    return this.httpClient.get<Schedule>(`${this.API_URL}/schedules/${id}`)
  }

  findByMedic(id: Number): Observable<Schedule[]> {
    return this.httpClient.get<Schedule[]>(`${this.API_URL}/schedules/medic/${id}`)
  }

  save(value: Schedule): Observable<Object> {
    if (value.id)
      return this.update(value)
    return this.create(value)
  }

  private create(value: Schedule): Observable<Object> {
    return this.httpClient.post(`${this.API_URL}/schedules`, value)
  }

  private update(value: Schedule): Observable<Object> {
    return this.httpClient.put(`${this.API_URL}/schedules/${value.id}`, value)
  }

  delete(id: Number): Observable<Object> {
    return this.httpClient.delete(`${this.API_URL}/schedules/${id}`)

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

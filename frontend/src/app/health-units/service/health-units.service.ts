import { Injectable } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class HealthUnitsService {
  private readonly API_URL: String = environment.API_URL;

  constructor(
    private httpClient: HttpClient,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) { }

  findAll(): Observable<HealthUnit[]> {
    return this.httpClient.get<HealthUnit[]>(`${this.API_URL}/health-units`)
  }

  findById(id: Number): Observable<HealthUnit> {
    return this.httpClient.get<HealthUnit>(`${this.API_URL}/health-units/${id}`)
  }

  save(value: HealthUnit): Observable<Object> {
    if (value.id)
      return this.update(value)
    return this.create(value)
  }

  private create(value: HealthUnit): Observable<Object> {
    return this.httpClient.post(`${this.API_URL}/health-units`, value)
  }

  private update(value: HealthUnit): Observable<Object> {
    return this.httpClient.put(`${this.API_URL}/health-units/${value.id}`, value)
  }

  delete(id: Number): Observable<Object> {
    return this.httpClient.delete(`${this.API_URL}/health-units/${id}`)

  }

  public onError(errorMsg: String) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    })
  }

  public onSuccess(msg: string) {
    this.snackBar.open(msg, "Close", { duration: 5000 })
  }
}

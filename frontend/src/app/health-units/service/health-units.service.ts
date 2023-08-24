import { Injectable } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class HealthUnitsService {
  private readonly API_URL: String = environment.API_URL;

  constructor(
    private httpClient: HttpClient,
    private snackBar: MatSnackBar
  ) { }

  listAll(): Observable<HealthUnit[]> {
    return this.httpClient.get<HealthUnit[]>(`${this.API_URL}/health-units`)
  }

  save(value: HealthUnit) {
    return this.httpClient.post<HealthUnit>(`${this.API_URL}/health-units`, value)
      .subscribe({
        next: res => this.onSuccess(),
        error: err => this.onError()
      })
  }

  onSuccess() {
    this.snackBar.open("Saved successfully", "Close", { duration: 5000 })
  }

  onError() {
    this.snackBar.open("Error on save", "Close", { duration: 5000 })
  }
}

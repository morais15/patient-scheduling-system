import { Injectable } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class HealthUnitsService {
  private readonly API_URL: String = environment.API_URL;

  constructor(private httpClient: HttpClient) { }

  listAll(): Observable<HealthUnit[]> {
    return this.httpClient.get<HealthUnit[]>(`${this.API_URL}/health-units`)
  }
}

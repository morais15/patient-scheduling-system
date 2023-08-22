import { Injectable } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HealthUnitsService {

  constructor(private httpClient: HttpClient) { }

  listAll(): Observable<HealthUnit[]> {
    return this.httpClient.get<HealthUnit[]>('')
  }
}

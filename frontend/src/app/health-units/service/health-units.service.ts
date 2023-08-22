import { Injectable } from '@angular/core';
import { HealthUnit } from '../domain/health-unit';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HealthUnitsService {

  constructor(private httpClient: HttpClient) { }

  list(): HealthUnit[] {
    return [{
      _id: 1,
      name: 'test',
      address: 'test add'
    }]
  }
}

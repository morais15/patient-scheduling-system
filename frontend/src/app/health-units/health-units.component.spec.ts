import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HealthUnitsComponent } from './health-units.component';

describe('HealthUnitsComponent', () => {
  let component: HealthUnitsComponent;
  let fixture: ComponentFixture<HealthUnitsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HealthUnitsComponent]
    });
    fixture = TestBed.createComponent(HealthUnitsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

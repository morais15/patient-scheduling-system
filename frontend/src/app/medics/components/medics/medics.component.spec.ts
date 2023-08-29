import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicsComponent } from './medics.component';

describe('MedicsComponent', () => {
  let component: MedicsComponent;
  let fixture: ComponentFixture<MedicsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicsComponent]
    });
    fixture = TestBed.createComponent(MedicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

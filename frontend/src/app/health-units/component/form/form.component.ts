import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HealthUnitsService } from '../../service/health-units.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { HealthUnit } from '../../domain/health-unit';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent {
  protected form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private healthUnitsService: HealthUnitsService,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
    const healthUnit: HealthUnit = this.activatedRoute.snapshot.data['healthUnit'];
    this.form = this.formBuilder.group(healthUnit)
  }

  onSave() {
    this.healthUnitsService.save(this.form.value)
    .subscribe({
      next: ()=> {
        this.healthUnitsService.onSuccess("Success on save")
        this.location.back()
      } 
    })
  }

  onCancel() {
    this.location.back()
  }
}

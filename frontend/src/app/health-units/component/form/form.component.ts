import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HealthUnitsService } from '../../service/health-units.service';
import { Location } from '@angular/common';

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
    private location: Location
  ) {
    this.form = this.formBuilder.group({
      id: null,
      name: null,
      address: null
    })
  }

  onSave() {
    this.healthUnitsService.save(this.form.value)
    this.location.back()
  }

  onCancel() {
    this.location.back()
  }
}

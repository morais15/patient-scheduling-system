import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
    this.form = this.formBuilder.group({
      id: healthUnit.id,
      name: [healthUnit.name, [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
      address: [healthUnit.address, [Validators.required, Validators.minLength(5), Validators.maxLength(100)]]
    })
  }

  onSave() {
    this.healthUnitsService.save(this.form.value)
      .subscribe({
        next: () => {
          this.healthUnitsService.onSuccess("Success on save")
          this.location.back()
        },
        error: () => this.healthUnitsService.onError("Error on save.")
      })
  }

  onCancel() {
    this.location.back()
  }

  getErrorMessage(fieldName: string): String {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Required field.'
    }

    if (field?.hasError('minlength')) {
      const minLength = field.errors?.['minlength']['requiredLength']
      return `Minimum length is ${minLength} characters.`
    }

    if (field?.hasError('maxlength')) {
      const maxLength = field.errors?.['maxlength']['requiredLength']
      return `Maximum length is ${maxLength} characters.`
    }

    return 'Invalid field'
  }
}

import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MedicsService } from '../../service/medics.service';
import { ActivatedRoute } from '@angular/router';
import { Medic } from '../../domain/medic';
import { Location } from '@angular/common';
import { HealthUnit } from 'src/app/health-units/domain/health-unit';
import { HealthUnitsService } from '../../../health-units/service/health-units.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent {
  protected form: FormGroup;
  protected healthUnits: HealthUnit[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private medicsService: MedicsService,
    private healthUnitsService: HealthUnitsService,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
    const medic: Medic = this.activatedRoute.snapshot.data['medic'];
    this.form = this.formBuilder.group({
      id: medic.id,
      healthUnitId: medic.healthUnit?.id,
      name: [medic.name, [Validators.required, Validators.minLength(5), Validators.maxLength(50)]],
      specialty: [medic.specialty, [Validators.required, Validators.minLength(5), Validators.maxLength(50)]]
    })

    this.healthUnitsService.findAll()
      .subscribe({
        next: value => this.healthUnits = value,
        error: () => this.healthUnitsService.onError("Error on get health units")
      })
  }

  onSave() {
    this.medicsService.save(this.form.value)
      .subscribe({
        next: () => {
          this.medicsService.onSuccess("Success on save")
          this.location.back()
        },
        error: () => this.medicsService.onError("Error on save.")
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

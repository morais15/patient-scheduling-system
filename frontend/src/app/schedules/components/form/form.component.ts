import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SchedulesService } from '../../service/schedules.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Schedule } from '../../domain/Schedule';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent {
  protected form: FormGroup;
  protected statusList: String[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private schedulesService: SchedulesService,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
    const schedule: Schedule = this.activatedRoute.snapshot.data['schedule'];
    this.form = this.formBuilder.group({
      id: schedule.id,
      dateTime: [schedule.dateTime, [Validators.required, Validators.pattern(/^\d{2}\/\d{2}\/\d{4} \d{2}\:\d{2}$/)]],
      status: [schedule.status, [Validators.required, Validators.minLength(2), Validators.maxLength(20)]]
    })

    this.schedulesService.findStatus()
      .subscribe({
        next: value => this.statusList = value,
        error: () => this.schedulesService.onError("Error on get status list")
      })
  }

  onSave() {
    this.schedulesService.save(this.form.value)
      .subscribe({
        next: () => {
          this.schedulesService.onSuccess("Success on save")
          this.location.back()
        },
        error: () => this.schedulesService.onError("Error on save")
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

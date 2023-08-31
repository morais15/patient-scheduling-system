import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { SchedulesService } from 'src/app/schedules/service/schedules.service';
import { Medic } from '../../domain/Medic';
import { ActivatedRoute } from '@angular/router';
import { MedicsService } from '../../service/medics.service';
import { GenerateSchedules } from '../../domain/GenerateSchedules';

@Component({
  selector: 'app-generate',
  templateUrl: './generate.component.html',
  styleUrls: ['./generate.component.scss']
})
export class GenerateComponent {
  protected form: FormGroup;
  protected statusList: String[] = [];
  private timePattern: RegExp = /^\d{2}\:\d{2}$/;
  private datePattern: RegExp = /^\d{2}\/\d{2}\/\d{4}$/
  private medic: Medic;

  constructor(
    private formBuilder: FormBuilder,
    private schedulesService: SchedulesService,
    private medicsService: MedicsService,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
    this.medic = this.activatedRoute.snapshot.data['medic'];

    this.form = this.formBuilder.group({
      status: ['', [Validators.required]],
      startTime: ['', [Validators.required, Validators.pattern(this.timePattern)]],
      endTime: ['', [Validators.required, Validators.pattern(this.timePattern)]],
      stepMinutes: [0, [Validators.required]],
      startDate: ['', [Validators.required, Validators.pattern(this.datePattern)]],
      scheduleDurationDays: [0, [Validators.required]],
      lunchTime: ['', [Validators.required, Validators.pattern(this.timePattern)]],
      lunchDurationMinutes: [0, [Validators.required]]
    });

    this.schedulesService.findStatus()
      .subscribe({
        next: value => this.statusList = value,
        error: () => this.schedulesService.onError("Error on get status list")
      })
  }

  onGenerate() {
    this.medicsService.generateSchedules(this.medic.id, this.form.value)
      .subscribe({
        next: () => {
          this.medicsService.onSuccess("Success on generate schedules")
          this.location.back()
        },
        error: () => this.medicsService.onError("Error on generate schedules")
      })
  }

  getErrorMessage(fieldName: string): String {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Required field.'
    }

    return 'Invalid field'
  }

  onCancel() {
    this.location.back()
  }
}

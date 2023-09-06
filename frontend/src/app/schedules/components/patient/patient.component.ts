import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Schedule } from '../../domain/schedule';
import { SchedulesService } from '../../service/schedules.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Patient } from 'src/app/patients/domain/patient';
import { PatientsService } from 'src/app/patients/service/patients.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent {
  protected form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private patientsService: PatientsService,
    private location: Location,
    private activatedRoute: ActivatedRoute
  ) {
    const schedule: Schedule = this.activatedRoute.snapshot.data['schedule'];

    this.form = this.formBuilder.group({
      scheduleId: schedule.id,
      patientId: schedule.patient?.id,
      name: schedule.patient?.name,
      identity: schedule.patient?.identity
    })
  }

  onSave() {
    let patient: Patient = { id: this.form.value?.patientId, name: this.form.value.name, identity: this.form.value.identity };

    this.patientsService.save(this.form.value.scheduleId, patient)
      .subscribe({
        next: () => {
          this.patientsService.onSuccess("Success on save")
          this.location.back()
        },
        error: () => this.patientsService.onError("Error on save")
      })
  }

  onCancel() {
    this.location.back()
  }
}

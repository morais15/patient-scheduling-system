import { Medic } from "src/app/medics/domain/medic";
import { Patient } from "src/app/patients/domain/patient";

export interface Schedule {
  id: Number,
  dateTime: String,
  status: String,
  medic: Medic | null,
  patient: Patient | null
}

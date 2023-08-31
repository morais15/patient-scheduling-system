import { Medic } from "src/app/medics/domain/medic";

export interface Schedule {
  id: Number,
  dateTime: String,
  status: String,
  medic: Medic | null
}

import { HealthUnit } from "src/app/health-units/domain/health-unit";

export interface Medic {
  id: Number,
  name: String,
  specialty: String,
  healthUnit: HealthUnit | null
}

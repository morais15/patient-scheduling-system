package patient.scheduling.system.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record HealthUnitDTO(@NotBlank String name, @NotBlank String address) {
}

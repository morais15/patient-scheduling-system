package patient.scheduling.system.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicDTO(
        @NotBlank
        String name,
        @NotBlank
        String specialty,
        @NotNull
        Long healthUnitId) {
}

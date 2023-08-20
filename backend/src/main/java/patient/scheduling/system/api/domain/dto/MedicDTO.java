package patient.scheduling.system.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record MedicDTO(
        @NotBlank
        String name,
        @NotBlank
        String specialty) {
}

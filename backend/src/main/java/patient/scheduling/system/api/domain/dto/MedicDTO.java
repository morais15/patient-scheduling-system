package patient.scheduling.system.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record MedicDTO(
        @NotBlank
        @Length(min = 5, max = 50)
        String name,
        @NotBlank
        @Length(min = 5, max = 50)
        String specialty,
        @NotNull
        Long healthUnitId) {
}

package patient.scheduling.system.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record HealthUnitDTO(
        @NotBlank
        @Length(min = 5, max = 50)
        String name,
        @NotBlank
        @Length(min = 5, max = 100)
        String address) {
}

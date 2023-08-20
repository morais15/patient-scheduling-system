package patient.scheduling.system.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import patient.scheduling.system.api.domain.enums.StatusENUM;

import java.time.LocalDateTime;

public record ScheduleDTO(@NotNull @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dateTime, @NotNull StatusENUM status) {
}

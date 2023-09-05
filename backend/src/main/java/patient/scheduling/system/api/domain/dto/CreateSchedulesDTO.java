package patient.scheduling.system.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import patient.scheduling.system.api.domain.enums.StatusENUM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateSchedulesDTO(
        @NotNull
        StatusENUM status,
        @NotNull
        @Schema(implementation = String.class, example = "31/12/1970 23:59", pattern = "dd/MM/yyyy HH:mm")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime startDateTime,
        @NotNull
        @Schema(implementation = String.class, example = "31/12/1970 23:59", pattern = "dd/MM/yyyy HH:mm")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime endDateTime,
        @NotNull
        Integer stepMinutes,
        @NotNull
        @Schema(implementation = String.class, example = "23:59", pattern = "HH:mm")
        @JsonFormat(pattern = "HH:mm")
        LocalTime lunchTime,
        @NotNull
        Integer lunchDurationMinutes) {
}

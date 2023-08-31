package patient.scheduling.system.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import patient.scheduling.system.api.domain.enums.StatusENUM;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateSchedulesDTO(
        @NotNull
        StatusENUM status,
        @NotNull
        @Schema(implementation = String.class, example = "00:00", pattern = "HH:mm")
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime,
        @NotNull
        @Schema(implementation = String.class, example = "00:00", pattern = "HH:mm")
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime,
        @NotNull
        Long stepMinutes,
        @NotNull
        @Schema(implementation = String.class, example = "01/01/1970", pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate startDate,
        @NotNull
        Integer scheduleDurationDays,
        @NotNull
        @Schema(implementation = String.class, example = "00:00", pattern = "HH:mm")
        @JsonFormat(pattern = "HH:mm")
        LocalTime lunchTime,
        @NotNull
        Integer lunchDurationMinutes) {
}

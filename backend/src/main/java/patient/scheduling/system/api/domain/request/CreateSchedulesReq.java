package patient.scheduling.system.api.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import patient.scheduling.system.api.domain.enums.StatusENUM;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateSchedulesReq(
        StatusENUM status,
        @Schema(implementation = String.class, example = "00:00", pattern = "HH:mm")
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime,
        @Schema(implementation = String.class, example = "00:00", pattern = "HH:mm")
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime,
        Long stepMinutes,
        @Schema(implementation = String.class, example = "01/01/1970", pattern = "dd/MM/yyyy")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate startDate,
        Integer scheduleDurationDays,
        @Schema(implementation = String.class, example = "00:00", pattern = "HH:mm")
        @JsonFormat(pattern = "HH:mm")
        LocalTime lunchTime,
        Integer lunchDurationMinutes) {
}

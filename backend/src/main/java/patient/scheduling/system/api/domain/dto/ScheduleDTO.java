package patient.scheduling.system.api.domain.dto;

import patient.scheduling.system.api.domain.enums.StatusENUM;

import java.time.Instant;

public record ScheduleDTO(Instant date, StatusENUM status) {
}

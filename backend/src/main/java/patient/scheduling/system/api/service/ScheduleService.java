package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patient.scheduling.system.api.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
}

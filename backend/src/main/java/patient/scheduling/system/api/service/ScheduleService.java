package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.repository.ScheduleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService implements BaseService<Schedule> {
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public Schedule findByIdOr404(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found"));
    }

    @Override
    @Transactional
    public Schedule save(Schedule schedule) {
        var saved = findByMedicIdAndDateTime(schedule.getMedic().getId(), schedule.getDateTime());
        if (saved.isPresent()) {
            saved.get().setStatus(schedule.getStatus());
            return scheduleRepository.save(saved.get());
        }

        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long id) {
        var schedule = findByIdOr404(id);
        scheduleRepository.delete(schedule);
    }

    public Optional<Schedule> findByMedicIdAndDateTime(Long medicId, LocalDateTime dateTime) {
        return scheduleRepository.findFirstByMedic_idAndDateTime(medicId, dateTime);
    }

    public List<Schedule> findByMedicId(Long id) {
        return scheduleRepository.findByMedic_id(id);
    }
}

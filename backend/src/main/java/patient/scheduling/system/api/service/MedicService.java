package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.entity.Medic;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.repository.MedicRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicService {
    private final MedicRepository medicRepository;
    private final ScheduleService scheduleService;

    public List<Medic> findAll() {
        return medicRepository.findAll();
    }

    protected Optional<Medic> findById(Long id) {
        return medicRepository.findById(id);
    }

    public Medic findByIdOr404(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medic not found."));
    }

    public List<Medic> findByHealthUnitId(Long id) {
        return medicRepository.findByHealthUnit_id(id);
    }

    @Transactional
    public void addSchedules(Long medicId, List<Schedule> schedules) {
        var medic = findByIdOr404(medicId);

        var schedulesSaved = schedules
                .stream()
                .map(schedule -> {
                    schedule.setMedic(medic);
                    return scheduleService.save(schedule);
                })
                .toList();

        medic.getSchedules().addAll(schedulesSaved);
        medicRepository.save(medic);
    }

    @Transactional
    public void save(Medic medic) {
        medicRepository.save(medic);
    }
}

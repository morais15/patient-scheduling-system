package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.entity.Medic;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.domain.enums.StatusENUM;
import patient.scheduling.system.api.repository.MedicRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public void createSchedules(Long medicId, StatusENUM status, LocalTime startTime, LocalTime endTime, Long stepMinutes, LocalDate startDate, Integer scheduleDurationDays, LocalTime lunchTime, Integer lunchDurationMinutes) {
        if (scheduleDurationDays > 180)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule duration days cannot be longer than 180");

        List<Schedule> schedules = new ArrayList<>();
        var actualDate = startDate;
        var actualTime = startTime;
        var dayOfWeek = actualDate.getDayOfWeek();

        for (int i = 0; i < scheduleDurationDays; i++) {
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                while (actualTime.isBefore(endTime)) {
                    if (actualTime.isBefore(lunchTime) || actualTime.isAfter(lunchTime.plusMinutes(lunchDurationMinutes - 1))) {
                        var dateTime = actualDate.atTime(actualTime);
                        var schedule = new Schedule(null, dateTime, status, null);
                        schedules.add(schedule);
                    }
                    actualTime = actualTime.plusMinutes(stepMinutes);
                }
            }

            actualDate = actualDate.plusDays(1);
            actualTime = startTime;
            dayOfWeek = actualDate.getDayOfWeek();
        }

        addSchedules(medicId, schedules);
    }

    @Transactional
    public void addSchedules(Long medicId, List<Schedule> schedules) {
        var medic = findByIdOr404(medicId);

        schedules.forEach(schedule -> {
            schedule.setMedic(medic);
            scheduleService.save(schedule);
        });
    }

    @Transactional
    public void save(Medic medic) {
        medicRepository.save(medic);
    }
}

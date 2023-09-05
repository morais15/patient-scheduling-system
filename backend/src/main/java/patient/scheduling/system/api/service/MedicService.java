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

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicService implements BaseService<Medic> {
    private final MedicRepository medicRepository;
    private final ScheduleService scheduleService;

    @Override
    public List<Medic> findAll() {
        return medicRepository.findAll();
    }

    @Override
    public Optional<Medic> findById(Long id) {
        return medicRepository.findById(id);
    }

    @Override
    public Medic findByIdOr404(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medic not found."));
    }

    @Override
    @Transactional
    public Medic save(Medic medic) {
        return medicRepository.save(medic);
    }

    @Override
    public void delete(Long id) {
        var medic = findByIdOr404(id);
        medicRepository.delete(medic);
    }

    public List<Medic> findByHealthUnitId(Long id) {
        return medicRepository.findByHealthUnit_id(id);
    }

    @Transactional
    public void createSchedules(Long medicId, StatusENUM status, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer stepMinutes, LocalTime lunchTime, Integer lunchDurationMinutes) {
        float duration = Duration.between(startDateTime, endDateTime).toHours() / 24f;

        if (duration > 30)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule duration days cannot be longer than 30");

        if (duration < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date can not be after than end date");

        List<Schedule> schedules = new ArrayList<>();

        var actualDateTime = startDateTime;

        int plusHours = Math.abs(endDateTime.getHour() - startDateTime.getHour());
        int plusMinutes = Math.abs(endDateTime.getMinute() - startDateTime.getMinute());
        var dayEndDateTime = startDateTime.plusHours(plusHours).plusMinutes(plusMinutes);

        var dayOfWeek = actualDateTime.getDayOfWeek();

        for (int i = 0; i < duration; i++) {
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                while (actualDateTime.isBefore(dayEndDateTime)) {
                    if (actualDateTime.toLocalTime().isBefore(lunchTime) || actualDateTime.toLocalTime().isAfter(lunchTime.plusMinutes(lunchDurationMinutes - 1))) {
                        var schedule = new Schedule(null, actualDateTime, status, null);
                        schedules.add(schedule);
                    }
                    actualDateTime = actualDateTime.plusMinutes(stepMinutes);
                }
            }

            actualDateTime = actualDateTime.plusDays(1);
            actualDateTime = actualDateTime.withHour(startDateTime.getHour()).withMinute(startDateTime.getMinute());

            plusHours = Math.abs(endDateTime.getHour() - actualDateTime.getHour());
            plusMinutes = Math.abs(endDateTime.getMinute() - actualDateTime.getMinute());
            dayEndDateTime = actualDateTime.plusHours(plusHours).plusMinutes(plusMinutes);

            dayOfWeek = actualDateTime.getDayOfWeek();
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
}

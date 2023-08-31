package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patient.scheduling.system.api.domain.dto.ScheduleDTO;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.domain.enums.StatusENUM;
import patient.scheduling.system.api.service.MedicService;
import patient.scheduling.system.api.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Schedule")
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final MedicService medicService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Schedule> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public StatusENUM[] findStatus() {
        return StatusENUM.values();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Schedule findById(@PathVariable Long id) {
        return scheduleService.findByIdOr404(id);
    }

    @GetMapping("/medic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Schedule> findByMedicId(@PathVariable Long id) {
        return scheduleService.findByMedicId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long scheduleId, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        var schedule = scheduleService.findByIdOr404(scheduleId);
        var medic = medicService.findByIdOr404(scheduleDTO.medicId());

        schedule.setMedic(medic);
        schedule.setDateTime(scheduleDTO.dateTime());
        schedule.setStatus(scheduleDTO.status());

        scheduleService.save(schedule);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        var medic = medicService.findByIdOr404(scheduleDTO.medicId());

        var schedule = new Schedule();
        schedule.setMedic(medic);
        BeanUtils.copyProperties(scheduleDTO, schedule);

        scheduleService.save(schedule);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        scheduleService.delete(id);
    }

}

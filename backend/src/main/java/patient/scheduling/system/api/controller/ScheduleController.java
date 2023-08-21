package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patient.scheduling.system.api.domain.dto.ScheduleDTO;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Schedule")
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Schedule> findAll() {
        return scheduleService.findAll();
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        var schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        scheduleService.save(schedule);
    }
}

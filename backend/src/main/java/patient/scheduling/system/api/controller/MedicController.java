package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patient.scheduling.system.api.domain.dto.MedicDTO;
import patient.scheduling.system.api.domain.dto.ScheduleDTO;
import patient.scheduling.system.api.domain.entity.Medic;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.domain.request.CreateSchedulesReq;
import patient.scheduling.system.api.service.MedicService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Medic")
@RequestMapping("/medic")
public class MedicController {
    private final MedicService medicService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Medic> findAll() {
        return medicService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Medic findById(@PathVariable Long id) {
        return medicService.findByIdOr404(id);
    }

    @GetMapping("/health-unit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Medic> findByHealthUnitId(@PathVariable Long id) {
        return medicService.findByHealthUnitId(id);
    }

    @PatchMapping("/{id}/schedules")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSchedules(@PathVariable("id") Long medicId, @RequestBody List<ScheduleDTO> scheduleDTOs) {
        var schedules = scheduleDTOs
                .stream()
                .map(scheduleDTO -> {
                    var schedule = new Schedule();
                    BeanUtils.copyProperties(scheduleDTO, schedule);
                    return schedule;
                })
                .toList();

        medicService.addSchedules(medicId, schedules);
    }

    @PostMapping("/{id}/create-schedules")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSchedules(@PathVariable("id") Long medicId, @RequestBody CreateSchedulesReq csr) {
        medicService.createSchedules(
                medicId,
                csr.status(),
                csr.startTime(),
                csr.endTime(),
                csr.stepMinutes(),
                csr.startDate(),
                csr.scheduleDurationDays(),
                csr.lunchTime(),
                csr.lunchDurationMinutes()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody MedicDTO dto) {
        var medic = new Medic();
        BeanUtils.copyProperties(dto, medic);

        medicService.save(medic);
    }
}

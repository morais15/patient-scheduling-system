package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patient.scheduling.system.api.domain.dto.CreateSchedulesDTO;
import patient.scheduling.system.api.domain.dto.MedicDTO;
import patient.scheduling.system.api.domain.dto.ScheduleDTO;
import patient.scheduling.system.api.domain.entity.Medic;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.service.HealthUnitService;
import patient.scheduling.system.api.service.MedicService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Medic")
@RequestMapping("/medics")
public class MedicController {
    private final MedicService medicService;
    private final HealthUnitService healthUnitService;

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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long medicId, @Valid @RequestBody MedicDTO medicDTO) {
        var medic = medicService.findByIdOr404(medicId);
        var healthUnit = healthUnitService.findByIdOr404(medicDTO.healthUnitId());

        medic.setHealthUnit(healthUnit);
        medic.setName(medicDTO.name());
        medic.setSpecialty(medicDTO.specialty());

        medicService.save(medic);
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
    public void createSchedules(@PathVariable("id") Long medicId, @RequestBody CreateSchedulesDTO csd) {
        medicService.createSchedules(
                medicId,
                csd.status(),
                csd.startDateTime(),
                csd.endDateTime(),
                csd.stepMinutes(),
                csd.lunchTime(),
                csd.lunchDurationMinutes()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody MedicDTO medicDTO) {
        var healthUnit = healthUnitService.findByIdOr404(medicDTO.healthUnitId());

        var medic = new Medic();
        medic.setHealthUnit(healthUnit);
        BeanUtils.copyProperties(medicDTO, medic);

        medicService.save(medic);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        medicService.delete(id);
    }
}

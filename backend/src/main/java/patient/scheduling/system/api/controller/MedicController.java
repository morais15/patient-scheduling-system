package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.dto.CreateSchedulesDTO;
import patient.scheduling.system.api.domain.dto.MedicDTO;
import patient.scheduling.system.api.domain.dto.ScheduleDTO;
import patient.scheduling.system.api.domain.entity.Medic;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.service.HealthUnitService;
import patient.scheduling.system.api.service.MedicService;

import java.util.List;

/**
 * @author Douglas Morais
 */

@RestController
@RequiredArgsConstructor
@Tag(name = "Medic")
@RequestMapping("/medics")
public class MedicController {
    private final MedicService medicService;
    private final HealthUnitService healthUnitService;

    /**
     * Find all medics
     * @return list of all medics
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all medics")
    public List<Medic> findAll() {
        return medicService.findAll();
    }

    /**
     * Find medic by id
     * @param id = id of medic
     * @return medic by id
     * @throws ResponseStatusException 404 if medic does not exist
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find medic by id")
    public Medic findById(@PathVariable Long id) {
        return medicService.findByIdOr404(id);
    }

    /**
     * Find medic by health unit id
     * @param id = id of health unit
     * @return list of medics by health unit id
     */
    @GetMapping("/health-unit/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find medic by health unit id")
    public List<Medic> findByHealthUnitId(@PathVariable Long id) {
        return medicService.findByHealthUnitId(id);
    }

    /**
     * Update medic
     * @param medicId = medic id
     * @param medicDTO = DTO of medic
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update medic")
    public void update(@PathVariable("id") Long medicId, @Valid @RequestBody MedicDTO medicDTO) {
        var medic = medicService.findByIdOr404(medicId);
        var healthUnit = healthUnitService.findByIdOr404(medicDTO.healthUnitId());

        medic.setHealthUnit(healthUnit);
        medic.setName(medicDTO.name());
        medic.setSpecialty(medicDTO.specialty());

        medicService.save(medic);
    }

    /**
     * Add schedules in medic
     * @param medicId = id of medic
     * @param scheduleDTOs = list of schedules DTOs to add in medic
     */
    @PatchMapping("/{id}/schedules")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Add schedules in medic")
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

    /**
     * Generate schedules in medic
     * @param medicId = id of medic
     * @param csd = DTO with data to generate schedules
     */
    @PostMapping("/{id}/create-schedules")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Generate schedules in medic")
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

    /**
     * Save medic
     * @param medicDTO DTO of medic
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save medic")
    public void save(@Valid @RequestBody MedicDTO medicDTO) {
        var healthUnit = healthUnitService.findByIdOr404(medicDTO.healthUnitId());

        var medic = new Medic();
        medic.setHealthUnit(healthUnit);
        BeanUtils.copyProperties(medicDTO, medic);

        medicService.save(medic);
    }

    /**
     * Delete medic
     * @param id = id of medic
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete medic")
    public void delete(@PathVariable Long id) {
        medicService.delete(id);
    }
}

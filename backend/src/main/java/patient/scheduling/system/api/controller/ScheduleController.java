package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.dto.PatientDTO;
import patient.scheduling.system.api.domain.dto.ScheduleDTO;
import patient.scheduling.system.api.domain.entity.Patient;
import patient.scheduling.system.api.domain.entity.Schedule;
import patient.scheduling.system.api.domain.enums.StatusENUM;
import patient.scheduling.system.api.service.MedicService;
import patient.scheduling.system.api.service.PatientService;
import patient.scheduling.system.api.service.ScheduleService;

import java.util.List;

/**
 * @author Douglas Morais
 */

@RestController
@RequiredArgsConstructor
@Tag(name = "Schedule")
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final MedicService medicService;
    private final PatientService patientService;

    /**
     * Find all schedules
     * @return list of all schedules
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all schedules")
    public List<Schedule> findAll() {
        return scheduleService.findAll();
    }

    /**
     * Find all status values
     * @return array of status
     */
    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all status values")
    public StatusENUM[] findStatus() {
        return StatusENUM.values();
    }

    /**
     * Find schedule by id
     * @param id = id of schedule
     * @return schedule by id
     * @throws ResponseStatusException 404 if schedule does not exist
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find schedule by id")
    public Schedule findById(@PathVariable Long id) {
        return scheduleService.findByIdOr404(id);
    }

    /**
     * Find schedules by medic id
     * @param id = medic id
     * @return list of schedules by medic id
     */
    @GetMapping("/medic/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find schedules by medic id")
    public List<Schedule> findByMedicId(@PathVariable Long id) {
        return scheduleService.findByMedicId(id);
    }

    /**
     * Update schedule
     * @param scheduleId = id of schedule
     * @param scheduleDTO = list of schedule DTOs
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update schedule")
    public void update(@PathVariable("id") Long scheduleId, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        var schedule = scheduleService.findByIdOr404(scheduleId);
        var medic = medicService.findByIdOr404(scheduleDTO.medicId());

        schedule.setMedic(medic);
        schedule.setDateTime(scheduleDTO.dateTime());
        schedule.setStatus(scheduleDTO.status());

        scheduleService.save(schedule);
    }

    /**
     * Update patient by schedule id
     * @param scheduleId = id of schedule
     * @param patientDTO = DTO of patient
     */
    @PutMapping("/{id}/patient")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update patient by schedule id")
    public void updatePatient(@PathVariable("id") Long scheduleId, @Valid @RequestBody PatientDTO patientDTO) {
        var schedule = scheduleService.findByIdOr404(scheduleId);
        var patientSaved = patientService.findByIdentity(patientDTO.identity());

        Patient patient = new Patient();

        if (patientSaved.isPresent())
            patient = patientSaved.get();
        else
            BeanUtils.copyProperties(patientDTO, patient);

        schedule.setPatient(patient);
        scheduleService.save(schedule);
    }

    /**
     * Save schedule
     * @param scheduleDTO = DTO of schedule
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save schedule")
    public void save(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        var medic = medicService.findByIdOr404(scheduleDTO.medicId());

        var schedule = new Schedule();
        schedule.setMedic(medic);
        BeanUtils.copyProperties(scheduleDTO, schedule);

        scheduleService.save(schedule);
    }

    /**
     * Delete schedule
     * @param id = id of schedule
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete schedule")
    public void delete(@PathVariable Long id) {
        scheduleService.delete(id);
    }

}

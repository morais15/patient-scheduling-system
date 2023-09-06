package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patient.scheduling.system.api.domain.dto.PatientDTO;
import patient.scheduling.system.api.domain.entity.Patient;
import patient.scheduling.system.api.service.PatientService;
import patient.scheduling.system.api.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Patient")
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private final ScheduleService scheduleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient findById(@PathVariable Long id) {
        return patientService.findByIdOr404(id);
    }

    @GetMapping("/schedule/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByScheduleId(@PathVariable Long id) {
        return patientService.findByScheduleId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody PatientDTO patientDTO) {
        var schedule = scheduleService.findByIdOr404(patientDTO.scheduleId());

        var patient = new Patient();
        patient.setSchedule(schedule);
        BeanUtils.copyProperties(patientDTO, patient);

        patientService.save(patient);
    }
}

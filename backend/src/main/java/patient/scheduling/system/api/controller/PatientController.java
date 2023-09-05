package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patient.scheduling.system.api.domain.entity.Patient;
import patient.scheduling.system.api.service.PatientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Patient")
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

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
}

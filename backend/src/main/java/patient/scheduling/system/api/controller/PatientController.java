package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.dto.PatientDTO;
import patient.scheduling.system.api.domain.entity.Patient;
import patient.scheduling.system.api.service.PatientService;

import java.util.List;

/**
 * @author Douglas Morais
 */

@RestController
@RequiredArgsConstructor
@Tag(name = "Patient")
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    /**
     * Find all patients
     * @return list of all patients
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all patients")
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    /**
     * Find patient by id
     * @param id = id of patient
     * @return patient by id
     * @throws ResponseStatusException 404 if patient does not exist
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find patient by id")
    public Patient findById(@PathVariable Long id) {
        return patientService.findByIdOr404(id);
    }

    /**
     * Update patient
     * @param id = id of patient
     * @param patientDTO = DTO os patient
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update patient")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody PatientDTO patientDTO) {
        var patient = patientService.findByIdOr404(id);
        patientService.findByIdentityOr404(patientDTO.identity());

        patient.setName(patientDTO.name());
        patient.setIdentity(patientDTO.identity());

        patientService.save(patient);
    }
}

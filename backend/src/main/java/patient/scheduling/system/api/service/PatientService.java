package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.entity.Patient;
import patient.scheduling.system.api.domain.enums.StatusENUM;
import patient.scheduling.system.api.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService implements BaseService<Patient> {
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient findByIdOr404(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    @Override
    @Transactional
    public Patient save(Patient value) {
        if (value.getSchedule().getStatus() != StatusENUM.FREE)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule status can be FREE");

        value.getSchedule().setStatus(StatusENUM.SCHEDULED);
        return patientRepository.save(value);
    }

    @Override
    public void delete(Long id) {
        var patient = findByIdOr404(id);
        patientRepository.delete(patient);
    }

    public List<Patient> findByScheduleId(Long scheduleId) {
        return patientRepository.findBySchedule_id(scheduleId);
    }
}

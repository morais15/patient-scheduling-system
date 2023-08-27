package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.entity.HealthUnit;
import patient.scheduling.system.api.repository.HealthUnitRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthUnitService {
    private final HealthUnitRepository healthUnitRepository;
    private final MedicService medicService;

    public List<HealthUnit> findAll() {
        return healthUnitRepository.findAll();
    }

    protected Optional<HealthUnit> findById(Long id) {
        return healthUnitRepository.findById(id);
    }

    public HealthUnit findByIdOr404(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Health unit not found."));
    }

    @Transactional
    public void addMedics(Long healthUnitId, List<Long> medicIds) {
        var healthUnit = findByIdOr404(healthUnitId);
        var medics = medicIds
                .stream()
                .map(medicService::findByIdOr404)
                .toList();

        medics.forEach(medic -> {
            medic.setHealthUnit(healthUnit);
            medicService.save(medic);
        });
    }

    @Transactional
    public void save(HealthUnit healthUnit) {
        healthUnitRepository.save(healthUnit);
    }

    public void delete(Long id) {
        var healthUnit = findByIdOr404(id);
        healthUnitRepository.delete(healthUnit);
    }
}

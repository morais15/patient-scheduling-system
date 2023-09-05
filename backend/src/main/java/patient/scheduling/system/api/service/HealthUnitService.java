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
public class HealthUnitService implements BaseService<HealthUnit> {
    private final HealthUnitRepository healthUnitRepository;
    private final MedicService medicService;

    @Override
    public List<HealthUnit> findAll() {
        return healthUnitRepository.findAll();
    }

    @Override
    public Optional<HealthUnit> findById(Long id) {
        return healthUnitRepository.findById(id);
    }

    @Override
    public HealthUnit findByIdOr404(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Health unit not found."));
    }

    @Override
    @Transactional
    public HealthUnit save(HealthUnit healthUnit) {
        return healthUnitRepository.save(healthUnit);
    }

    @Override
    public void delete(Long id) {
        var healthUnit = findByIdOr404(id);
        healthUnitRepository.delete(healthUnit);
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
}

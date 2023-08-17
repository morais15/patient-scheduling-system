package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import patient.scheduling.system.api.domain.entity.HealthUnit;
import patient.scheduling.system.api.repository.HealthUnitRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthUnitService {
    private final HealthUnitRepository healthUnitRepository;

    public List<HealthUnit> findAll() {
        return healthUnitRepository.findAll();
    }

    @Transactional
    public void save(HealthUnit healthUnit) {
        healthUnitRepository.save(healthUnit);
    }
}

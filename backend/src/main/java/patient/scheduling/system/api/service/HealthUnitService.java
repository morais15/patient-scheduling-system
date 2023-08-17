package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patient.scheduling.system.api.repository.HealthUnitRepository;

@Service
@RequiredArgsConstructor
public class HealthUnitService {
    private final HealthUnitRepository healthUnitRepository;
}

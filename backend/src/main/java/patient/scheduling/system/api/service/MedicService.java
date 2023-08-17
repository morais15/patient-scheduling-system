package patient.scheduling.system.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patient.scheduling.system.api.repository.MedicRepository;

@Service
@RequiredArgsConstructor
public class MedicService {
    private final MedicRepository medicRepository;
}

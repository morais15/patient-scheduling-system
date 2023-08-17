package patient.scheduling.system.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patient.scheduling.system.api.service.HealthUnitService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/health-unit")
public class HealthUnitController {
    private final HealthUnitService healthUnitService;
}

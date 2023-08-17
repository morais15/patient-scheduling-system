package patient.scheduling.system.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patient.scheduling.system.api.service.MedicService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medic")
public class MedicController {
    private final MedicService medicService;
}

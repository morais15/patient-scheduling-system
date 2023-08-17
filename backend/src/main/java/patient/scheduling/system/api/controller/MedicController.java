package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patient.scheduling.system.api.service.MedicService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Medic")
@RequestMapping("/medic")
public class MedicController {
    private final MedicService medicService;
}

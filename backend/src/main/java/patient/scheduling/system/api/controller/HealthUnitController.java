package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import patient.scheduling.system.api.domain.dto.HealthUnitDTO;
import patient.scheduling.system.api.domain.entity.HealthUnit;
import patient.scheduling.system.api.service.HealthUnitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Health Unit")
@RequestMapping("/health-unit")
public class HealthUnitController {
    private final HealthUnitService healthUnitService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HealthUnit> findAll() {
        return healthUnitService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody HealthUnitDTO dto){
        var healthUnit = new HealthUnit();
        BeanUtils.copyProperties(dto, healthUnit);

        healthUnitService.save(healthUnit);
    }
}

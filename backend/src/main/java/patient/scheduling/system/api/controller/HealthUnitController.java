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
@RequestMapping("/health-units")
public class HealthUnitController {
    private final HealthUnitService healthUnitService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HealthUnit> findAll() {
        return healthUnitService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HealthUnit findById(@PathVariable Long id) {
        return healthUnitService.findByIdOr404(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long healthUnitId, @Valid @RequestBody HealthUnitDTO healthUnitDTO) {
        HealthUnit healthUnit = healthUnitService.findByIdOr404(healthUnitId);
        healthUnit.setName(healthUnitDTO.name());
        healthUnit.setAddress(healthUnitDTO.address());

        healthUnitService.save(healthUnit);
    }

    @PatchMapping("/{id}/medics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMedics(@PathVariable("id") Long healthUnitId, @RequestBody List<Long> medicIds) {
        healthUnitService.addMedics(healthUnitId, medicIds);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody HealthUnitDTO healthUnitDTO) {
        var healthUnit = new HealthUnit();
        BeanUtils.copyProperties(healthUnitDTO, healthUnit);

        healthUnitService.save(healthUnit);
    }
}

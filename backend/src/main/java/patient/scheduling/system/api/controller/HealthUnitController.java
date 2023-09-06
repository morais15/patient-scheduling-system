package patient.scheduling.system.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import patient.scheduling.system.api.domain.dto.HealthUnitDTO;
import patient.scheduling.system.api.domain.entity.HealthUnit;
import patient.scheduling.system.api.service.HealthUnitService;

import java.util.List;

/**
 * @author Douglas Morais
 */

@RestController
@RequiredArgsConstructor
@Tag(name = "Health Unit")
@RequestMapping("/health-units")
public class HealthUnitController {
    private final HealthUnitService healthUnitService;

    /**
     * Find all health units
     * @return list of all health units
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all health units")
    public List<HealthUnit> findAll() {
        return healthUnitService.findAll();
    }

    /**
     * Find health unit by id
     * @param id = id of health unit
     * @return health unit by id
     * @throws ResponseStatusException 404 if health unit does not exist
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find health unit by id")
    public HealthUnit findById(@PathVariable Long id) {
        return healthUnitService.findByIdOr404(id);
    }

    /**
     * Update health unit
     * @param healthUnitId = id of health unit
     * @param healthUnitDTO = DTO of health unit
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update health unit")
    public void update(@PathVariable("id") Long healthUnitId, @Valid @RequestBody HealthUnitDTO healthUnitDTO) {
        var healthUnit = healthUnitService.findByIdOr404(healthUnitId);
        healthUnit.setName(healthUnitDTO.name());
        healthUnit.setAddress(healthUnitDTO.address());

        healthUnitService.save(healthUnit);
    }

    /**
     * Add medics in health unit
     * @param healthUnitId = id of health unit
     * @param medicIds = list of medics ids
     */
    @PatchMapping("/{id}/medics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Add medics in health unit")
    public void addMedics(@PathVariable("id") Long healthUnitId, @RequestBody List<Long> medicIds) {
        healthUnitService.addMedics(healthUnitId, medicIds);
    }

    /**
     * Save health unit
     * @param healthUnitDTO = DTO of health unit
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save health unit")
    public void save(@Valid @RequestBody HealthUnitDTO healthUnitDTO) {
        var healthUnit = new HealthUnit();
        BeanUtils.copyProperties(healthUnitDTO, healthUnit);

        healthUnitService.save(healthUnit);
    }

    /**
     * Delete health unit
     * @param id = id of health unit
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete health unit")
    public void delete(@PathVariable Long id) {
        healthUnitService.delete(id);
    }
}

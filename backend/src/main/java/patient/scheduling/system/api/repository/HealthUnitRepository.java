package patient.scheduling.system.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patient.scheduling.system.api.domain.entity.HealthUnit;

@Repository
public interface HealthUnitRepository extends JpaRepository<HealthUnit, Long> {
}

package patient.scheduling.system.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patient.scheduling.system.api.domain.entity.Medic;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {
}

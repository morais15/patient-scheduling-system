package patient.scheduling.system.api.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T findByIdOr404(Long id);

    T save(T value);

    void delete(Long id);
}

package patient.scheduling.system.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;
    @ManyToOne
    private HealthUnit healthUnit;
    @OneToMany(mappedBy = "medic")
    private List<Schedule> schedules;
}

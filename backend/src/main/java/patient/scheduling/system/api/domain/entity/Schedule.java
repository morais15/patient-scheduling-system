package patient.scheduling.system.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import patient.scheduling.system.api.domain.enums.StatusENUM;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private StatusENUM status;
    @ManyToOne
    private Medic medic;
}

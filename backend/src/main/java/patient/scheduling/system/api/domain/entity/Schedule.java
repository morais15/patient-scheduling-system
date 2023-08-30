package patient.scheduling.system.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private StatusENUM status;
    @ManyToOne
    private Medic medic;
}

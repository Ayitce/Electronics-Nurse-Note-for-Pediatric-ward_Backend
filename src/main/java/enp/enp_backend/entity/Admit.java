package enp.enp_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Admit {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @OneToOne
    Bed bed;

    @OneToOne
    Room room;

    @OneToOne
    Patient patient;

    String admitDateTime;
    String dischargeDate;

    String an;
}

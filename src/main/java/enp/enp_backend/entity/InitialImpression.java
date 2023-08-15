package enp.enp_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InitialImpression {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    Boolean scalene_muscle;
    Boolean irritable;
    Boolean stupor_drownsiness;
    Boolean dehedration;
    Boolean nasal_flaring;
    Boolean subcostral_retraction;
    Boolean supersternal_retraction;
    Boolean grunting;
    Boolean pale_cyanosis;
    Boolean motting_skin;
    Boolean petichea;
}

package enp.enp_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RiskFactor {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    Boolean suspected_infection;
    Boolean organtranplantation;
    Boolean history_bone_marrow;
    Boolean primary_immune_defencing;
    Boolean postSplenectomy_asplenia;
    Boolean malignancy;
    Boolean bedRidden_cerebralPulsy;
    Boolean center_iv_catheter;
}

package enp.enp_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Triage {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @ManyToOne
    Admit admit;

    @OneToOne
    Indicator indicator;

    @OneToOne
    VitalSign vitalSign;

    @OneToOne
    ADD add;

    @OneToOne
    InitialImpression initialImpression;

    @OneToOne
    RiskFactor riskFactor;

    @OneToOne
    PhysicalExam physicalExam;
}

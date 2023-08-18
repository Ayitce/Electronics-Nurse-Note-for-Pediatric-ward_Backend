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

    //    @OneToOne
//    Indicator indicator;
    Boolean respiratory;
    Boolean sepsis;
    Boolean shock;
    Boolean seizure;

//    @OneToOne
//    VitalSign vitalSign;

    int heartRate;
    int respiratoryRate;
    double temperature;
    int oxygenSaturation;
    int oxygenTherapy;
    int systolic_blood_pressure;
    int diastolic_blood_pressure;

//
//    @OneToOne
//    ADD add;

    Boolean poor_feeding;
    Boolean history_of_seizure;
    Boolean generalize_seizure;
    Boolean comoatose_stage_seizure;
    int GCS;
//
//    @OneToOne
//    InitialImpression initialImpression;

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
//
//    @OneToOne
//    RiskFactor riskFactor;

    Boolean suspected_infection;
    Boolean organtranplantation;
    Boolean history_bone_marrow;
    Boolean primary_immune_defencing;
    Boolean postSplenectomy_asplenia;
    Boolean malignancy;
    Boolean bedRidden_cerebralPulsy;
    Boolean center_iv_catheter;
//
//    @OneToOne
//    PhysicalExam physicalExam;

    Boolean weak_pulse;
    Boolean bounding_pulse;
    Boolean cap_refill;
    Boolean flash_cap;
    Consciousness consciousness;
    int airEntry;
    int wheezing;
}

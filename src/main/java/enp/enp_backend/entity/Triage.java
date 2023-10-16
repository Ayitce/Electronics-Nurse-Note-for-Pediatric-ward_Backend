package enp.enp_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    String date;

    String nurseName;

//    Indicator
    Boolean respiratory;
    Boolean sepsis;
    Boolean shock;
    Boolean seizure;

//    VitalSign

    int heartRate;
    int respiratoryRate;
    double temperature;
    int oxygenSaturation;
    int oxygenTherapy;
    int systolic_blood_pressure;
    int diastolic_blood_pressure;

//    ADD

    Boolean poor_feeding;
    Boolean history_of_seizure;
    Boolean generalize_seizure;
    Boolean comoatose_stage_seizure;
    int GCS;
    int e;
    int v;
    int m;

//    InitialImpression

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

//    RiskFactor

    Boolean suspected_infection;
    Boolean organtranplantation;
    Boolean history_bone_marrow;
    Boolean primary_immune_defencing;
    Boolean postSplenectomy_asplenia;
    Boolean malignancy;
    Boolean bedRidden_cerebralPulsy;
    Boolean center_iv_catheter;

//    PhysicalExam;

    Boolean weak_pulse;
    Boolean bounding_pulse;
    Boolean cap_refill;
    Boolean flash_cap;
    Consciousness consciousness;
    int airEntry;
    int wheezing;

    //result
    int mpew;
    int severity;
    String result_respiratory;
    String result_sepsis;
    String result_shock;
    String result_seizure;
}

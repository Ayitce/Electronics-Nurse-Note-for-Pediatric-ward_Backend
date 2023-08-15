package enp.enp_backend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VitalSign {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    int heartRate;
    int respiratoryRate;
    double temperature;
    int oxygenSaturation;
    int oxygenTherapy;
    int systolic_blood_pressure;
    int diastolic_blood_pressure;

}
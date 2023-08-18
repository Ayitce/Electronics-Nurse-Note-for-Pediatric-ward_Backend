package enp.enp_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VitalSignDTO {

    int heartRate;
    int respiratoryRate;
    double temperature;
    int oxygenSaturation;
    int oxygenTherapy;
    int systolic_blood_pressure;
    int diastolic_blood_pressure;

}

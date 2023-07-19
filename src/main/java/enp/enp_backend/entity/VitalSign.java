package enp.enp_backend.entity;


import jakarta.persistence.Entity;
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

    private Long id;

    Date dateOfBirth;
    int heartRate;
    int respiratoryRate;
    double temperature;
    int oxygenSaturation;
    int oxygenTherapy;
    Consciousness consciousness;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
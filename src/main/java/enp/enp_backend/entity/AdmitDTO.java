package enp.enp_backend.entity;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmitDTO {
    Long id;

    Bed bed;

    Room room;

    PatientDTO patient;

    String admitDateTime;
    String dischargeDate;

    String an;

}

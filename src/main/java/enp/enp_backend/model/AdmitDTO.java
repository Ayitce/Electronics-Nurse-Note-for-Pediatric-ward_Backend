package enp.enp_backend.model;

import enp.enp_backend.entity.Bed;
import enp.enp_backend.entity.Room;
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

    RoomOnlyDTO room;

    PatientDTO patient;

    String admitDateTime;
    String dischargeDate;
    String age;

    String an;

}

package enp.enp_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ADDDTO {

    Boolean poor_feeding;
    Boolean history_of_seizure;
    Boolean generalize_seizure;
    Boolean comoatose_stage_seizure;
    int GCS;
}

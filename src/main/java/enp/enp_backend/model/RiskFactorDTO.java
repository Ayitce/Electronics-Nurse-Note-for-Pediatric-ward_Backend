package enp.enp_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskFactorDTO {

    Boolean suspected_infection;
    Boolean organtranplantation;
    Boolean history_bone_marrow;
    Boolean primary_immune_defencing;
    Boolean postSplenectomy_asplenia;
    Boolean malignancy;
    Boolean bedRidden_cerebralPulsy;
    Boolean center_iv_catheter;
}

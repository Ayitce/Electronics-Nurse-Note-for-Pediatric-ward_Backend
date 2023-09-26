package enp.enp_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InitialImpressionDTO {

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
}

package enp.enp_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorDTO {
    Boolean respiratory;
    Boolean sepsis;
    Boolean shock;
    Boolean seizure;
}

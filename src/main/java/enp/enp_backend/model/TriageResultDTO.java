package enp.enp_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriageResultDTO {
    int mpew;
    String result_respiratory;
    String result_sepsis;
    String result_shock;
    String result_seizure;
}

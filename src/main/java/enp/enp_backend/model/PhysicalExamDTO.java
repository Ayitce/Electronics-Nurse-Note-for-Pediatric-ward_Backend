package enp.enp_backend.model;

import enp.enp_backend.entity.Consciousness;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalExamDTO {

    Boolean weak_pulse;
    Boolean bounding_pulse;
    Boolean cap_refill;
    Boolean flash_cap;
    Consciousness consciousness;
    int airEntry;
    int wheezing;
}

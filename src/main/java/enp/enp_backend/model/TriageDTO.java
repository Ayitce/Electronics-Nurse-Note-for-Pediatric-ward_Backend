package enp.enp_backend.model;

import enp.enp_backend.entity.Bed;
import enp.enp_backend.entity.Room;
import enp.enp_backend.util.LabMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriageDTO {
    Long id;

    // AdmitDTO admit;
    String date;
    IndicatorDTO indicator;
    VitalSignDTO vitalSign;
    ADDDTO add;
    InitialImpressionDTO initialImpression;
    RiskFactorDTO riskFactor;
    PhysicalExamDTO physicalExam;
    TriageResultDTO triageResultDTO;

}

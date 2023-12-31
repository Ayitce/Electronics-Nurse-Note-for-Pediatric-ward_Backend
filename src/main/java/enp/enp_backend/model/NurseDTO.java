package enp.enp_backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NurseDTO {
    Long id;
    String name;
    String surname;
    String phoneNumber;
    String medicalID;
    String gender;

}

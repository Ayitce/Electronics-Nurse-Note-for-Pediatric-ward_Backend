package enp.enp_backend.entity;


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
    String nurseID;
    String gender;
    String dateOfBirth;

}

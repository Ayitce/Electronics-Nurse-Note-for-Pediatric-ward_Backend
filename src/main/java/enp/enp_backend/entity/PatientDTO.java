package enp.enp_backend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    Long id;

    String name;
    String surname;
    Integer age;
    String gender;
    String dateOfBirth;
    String address;
    String phoneNumber;
    String email;
    Boolean admitted;
    String admitDate;
    String dischargeDate;
    String medicalHistory;
    String allergies;
    String AN;
    String imageUrls;
}

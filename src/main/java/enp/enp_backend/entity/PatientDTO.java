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
    String nationalID;
    Boolean admitted;
    String admitDate;
    String dischargeDate;
    String medicalHistory;
    String bloodType;
    String AN;
    String imageUrls;
    String parentName;
    String height;
    String weight;
}

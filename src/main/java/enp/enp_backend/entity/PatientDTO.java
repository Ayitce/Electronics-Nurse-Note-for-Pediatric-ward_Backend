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
    String age;
    String gender;
    String dateOfBirth;
    String address;
    String phoneNumber;
    String IDcard;
    Boolean admitted;
    String admitDateTime;
    String dischargeDate;
    String allergies;
    String bloodType;
    String AN;
    String image;
    String parentName;
    String height;
    String weight;
    String symptom;
}

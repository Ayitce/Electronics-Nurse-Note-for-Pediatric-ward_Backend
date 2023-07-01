package enp.enp_backend.model;


import enp.enp_backend.entity.Doctor;
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
    String idCard;
    String allergies;
    String bloodType;
    String hn;
    String image;
    String parentName;
    String height;
    String weight;
    String symptom;

    Doctor doctor;
}

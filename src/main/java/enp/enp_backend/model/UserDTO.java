package enp.enp_backend.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
   // String email;
    String password;
   // String phoneNumber;
    Boolean enabled;

    List<String> authorities;

    NurseDTO nurse;
    DoctorDTO doctor;
}

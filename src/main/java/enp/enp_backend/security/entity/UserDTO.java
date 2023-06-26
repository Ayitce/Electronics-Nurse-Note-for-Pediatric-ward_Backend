package enp.enp_backend.security.entity;

import enp.enp_backend.entity.DoctorDTO;
import enp.enp_backend.entity.NurseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String username;
   // String email;
    String password;
   // String phoneNumber;

    List<String> authorities;

    NurseDTO nurse;
    DoctorDTO doctor;
}

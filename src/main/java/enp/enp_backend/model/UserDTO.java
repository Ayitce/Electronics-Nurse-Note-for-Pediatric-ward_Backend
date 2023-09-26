package enp.enp_backend.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return username.equals(userDTO.username) && password.equals(userDTO.password) && enabled.equals(userDTO.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled);
    }
}

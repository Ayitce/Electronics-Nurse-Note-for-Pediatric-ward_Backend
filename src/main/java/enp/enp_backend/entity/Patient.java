package enp.enp_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

package enp.enp_backend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Nurse {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    String name;
    String surname;
    String phoneNumber;
    String medicalID;
    String gender;


    @OneToOne
    User user;
}

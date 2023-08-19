package enp.enp_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Admit {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @ManyToOne
    Bed bed;

    @ManyToOne
    Room room;

    @ManyToOne
    Patient patient;

    String admitDateTime;
    String dischargeDate;
    String age;
    String an;

    @Builder.Default
    @OneToMany
    List<Triage> triages = new ArrayList<>();
}

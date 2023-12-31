package enp.enp_backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.security.entity.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Builder
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @jakarta.persistence.Id
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @OneToOne
    Nurse nurse;

    @OneToOne
    Doctor doctor;

}
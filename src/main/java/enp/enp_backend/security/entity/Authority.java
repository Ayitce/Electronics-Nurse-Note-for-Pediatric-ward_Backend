package enp.enp_backend.security.entity;


import enp.enp_backend.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Authority {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AuthorityName name;
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

    public Authority() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
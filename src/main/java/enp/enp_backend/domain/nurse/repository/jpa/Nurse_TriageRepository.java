package enp.enp_backend.domain.nurse.repository.jpa;

import enp.enp_backend.entity.Patient;
import enp.enp_backend.entity.Triage;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("db")
@Repository
public interface Nurse_TriageRepository extends JpaRepository<Triage, Long> {
}

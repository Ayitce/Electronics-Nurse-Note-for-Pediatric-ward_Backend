package enp.enp_backend.domain.nurse.repository.jpa;

import enp.enp_backend.entity.Bed;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("db")
@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
}

package enp.enp_backend.domain.nurse.repository.jpa;

import enp.enp_backend.entity.Nurse;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Profile("db")
@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
    List<Nurse> findAll();

}

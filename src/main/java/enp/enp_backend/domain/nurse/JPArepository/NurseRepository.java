package enp.enp_backend.domain.nurse.JPArepository;

import enp.enp_backend.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    List<Nurse> findAll();

}

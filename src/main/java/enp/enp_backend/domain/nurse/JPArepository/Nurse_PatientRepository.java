package enp.enp_backend.domain.nurse.JPArepository;

import enp.enp_backend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Nurse_PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findAll();
}

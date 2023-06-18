package enp.enp_backend.domain.doctor.JPArepository;

import enp.enp_backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAll();
}

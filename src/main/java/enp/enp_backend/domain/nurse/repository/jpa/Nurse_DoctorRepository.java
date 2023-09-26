package enp.enp_backend.domain.nurse.repository.jpa;

import enp.enp_backend.entity.Doctor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("db")
@Repository
public interface Nurse_DoctorRepository extends JpaRepository<Doctor, Long> {
    //List<Doctor> findAll();
}

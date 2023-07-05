package enp.enp_backend.domain.admin.repository.jpa;

import enp.enp_backend.entity.Doctor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("db")
@Repository
public interface Admin_DoctorRepository extends JpaRepository<Doctor, Long> {
}

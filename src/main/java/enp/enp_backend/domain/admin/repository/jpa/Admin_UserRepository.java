package enp.enp_backend.domain.admin.repository.jpa;

import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.User;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("db")
@Repository
public interface Admin_UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByAuthoritiesName(AuthorityName role);
    List<User> findByUsernameIgnoreCaseContainingOrNurseNameIgnoreCaseContainingOrNurseSurnameIgnoreCaseContainingOrDoctorNameIgnoreCaseContainingOrDoctorSurnameIgnoreCaseContaining(String username,String nurseName,String nurseSurname,String doctorName, String doctorSurname);
}

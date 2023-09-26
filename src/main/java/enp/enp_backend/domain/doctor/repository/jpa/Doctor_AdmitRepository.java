package enp.enp_backend.domain.doctor.repository.jpa;

import enp.enp_backend.entity.Admit;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("db")
@Repository
public interface Doctor_AdmitRepository  extends JpaRepository<Admit, Long> {
    List<Admit> findAll();
    Admit findAdmitByAn(String an);
    List<Admit> findByPatientNameIgnoreCaseContainingOrPatientSurnameIgnoreCaseContainingOrPatientHnIgnoreCaseContainingOrAnIgnoreCaseContaining(String name,String surname, String hn,String an);
}

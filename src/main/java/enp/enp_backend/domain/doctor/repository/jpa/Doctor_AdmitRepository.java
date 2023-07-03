package enp.enp_backend.domain.doctor.repository.jpa;

import enp.enp_backend.entity.Admit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Doctor_AdmitRepository  extends JpaRepository<Admit, Long> {
    List<Admit> findAll();
    Admit findAdmitByAn(String an);
    List<Admit> findByPatientNameIgnoreCaseContainingOrPatientSurnameIgnoreCaseContainingOrPatientHnIgnoreCaseContainingOrAnIgnoreCaseContaining(String name,String surname, String hn,String an);
}

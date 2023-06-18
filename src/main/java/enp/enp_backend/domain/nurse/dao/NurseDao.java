package enp.enp_backend.domain.nurse.dao;

import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NurseDao {

    Nurse save(Nurse nurse);
    Nurse getNurse(Long id);

    //-------------------------------------

    Integer getPatientSize();
    Page<Patient> getPatients(Integer pageSize, Integer page);
    Patient getPatient(Long id);
    Page<Patient> getPatient(Pageable pageRequest);
    Patient save(Patient patient);
    Optional<Patient> findByID(Long id);
}

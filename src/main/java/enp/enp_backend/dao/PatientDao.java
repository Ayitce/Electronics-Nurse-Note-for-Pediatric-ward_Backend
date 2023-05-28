package enp.enp_backend.dao;

import enp.enp_backend.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PatientDao {
    Integer getPatientSize();
    Page<Patient> getPatients(Integer pageSize, Integer page);
    Patient getPatient(Long id);
    Page<Patient> getPatient(Pageable pageRequest);
    Patient save(Patient patient);
    Optional<Patient> findByID(Long id);
}

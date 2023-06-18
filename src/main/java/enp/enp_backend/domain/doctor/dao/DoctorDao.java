package enp.enp_backend.domain.doctor.dao;

import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DoctorDao {

    Doctor save(Doctor doctor);
    Doctor getDoctor(Long id);

    //------------------------


    Integer getPatientSize();
    Page<Patient> getPatients(Integer pageSize, Integer page);
    Patient getPatient(Long id);
    Page<Patient> getPatient(Pageable pageRequest);
    Patient save(Patient patient);
    Optional<Patient> findByID(Long id);
}

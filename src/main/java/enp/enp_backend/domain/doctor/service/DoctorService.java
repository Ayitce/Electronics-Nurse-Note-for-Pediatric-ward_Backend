package enp.enp_backend.domain.doctor.service;

import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService {

    Doctor save(Doctor doctor);

    Doctor getDoctor(Long id);

    //-----------------------------


    Integer getPatientSize();

    Page<Patient> getPatient(Integer pageSize, Integer page);

    Patient getPatient(Long id);

    Patient save(Patient patient);

    List<Patient> getAllpatient();
}

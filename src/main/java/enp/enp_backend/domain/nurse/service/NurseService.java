package enp.enp_backend.domain.nurse.service;

import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NurseService {


    Nurse save(Nurse nurse);
    Nurse getNurse(Long id);

    //--------------------------------

    Integer getPatientSize();

    Page<Patient> getPatient(Integer pageSize, Integer page);
    Patient getPatient(Long id);
    Patient save(Patient patient);

    List<Patient> getAllpatient();
}

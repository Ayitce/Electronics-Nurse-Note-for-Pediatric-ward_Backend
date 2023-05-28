package enp.enp_backend.service;

import enp.enp_backend.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PatientService {
    Integer getPatientSize();

    Page<Patient> getPatient(Integer pageSize,Integer page);
    Patient getPatient(Long id);
    Patient save(Patient patient);

    List<Patient> getAllpatient();
}

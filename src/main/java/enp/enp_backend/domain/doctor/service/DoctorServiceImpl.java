package enp.enp_backend.domain.doctor.service;

import enp.enp_backend.domain.doctor.repository.jpa.DoctorRepository;
import enp.enp_backend.domain.doctor.repository.jpa.Doctor_PatientRepository;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Doctor_PatientRepository doctorPatientRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }


    //---------------------------------


    @Override
    public Integer getPatientSize() {
        return Math.toIntExact(doctorPatientRepository.count());
    }

    @Override
    public Page<Patient> getPatient(Integer pageSize, Integer page) {
        return doctorPatientRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Patient getPatient(Long id) {
        return doctorPatientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        return doctorPatientRepository.save(patient);
    }

    ;

    @Override
    public List<Patient> getAllpatient() {
        return doctorPatientRepository.findAll(Pageable.unpaged()).getContent();
    }

    ;

}

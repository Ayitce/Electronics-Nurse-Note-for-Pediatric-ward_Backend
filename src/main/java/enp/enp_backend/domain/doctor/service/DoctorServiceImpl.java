package enp.enp_backend.domain.doctor.service;

import enp.enp_backend.domain.doctor.repository.jpa.DoctorRepository;
import enp.enp_backend.domain.doctor.repository.jpa.Doctor_AdmitRepository;
import enp.enp_backend.domain.doctor.repository.jpa.Doctor_PatientRepository;
import enp.enp_backend.entity.Admit;
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

    @Autowired
    Doctor_AdmitRepository doctor_admitRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    //----------Admit------------

    @Override
    public List<Admit> getAllAdmit() {
        return doctor_admitRepository.findAll(Pageable.unpaged()).getContent();
    }

    @Override
    public Admit getAdmitByAn(String an) {
        return doctor_admitRepository.findAdmitByAn(an);
    }

    @Override
    public List<Admit> getSearchedAdmit(String name, String surname, String hn, String an) {
        return doctor_admitRepository.findByPatientNameIgnoreCaseContainingOrPatientSurnameIgnoreCaseContainingOrPatientHnIgnoreCaseContainingOrAnIgnoreCaseContaining(name, surname, hn, an);
    }


}

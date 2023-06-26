package enp.enp_backend.domain.nurse.service;

import enp.enp_backend.domain.nurse.repository.jpa.NurseRepository;
import enp.enp_backend.domain.nurse.repository.jpa.Nurse_PatientRepository;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired
    NurseRepository nurseRepository;

    @Autowired
    Nurse_PatientRepository nursePatientRepository;

    @Override
    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    @Override
    public Nurse getNurse(Long id) {
        return nurseRepository.findById(id).orElse(null);
    }

    //-----------------------

    @Override
    public Integer getPatientSize() {
        return Math.toIntExact(nursePatientRepository.count());
    }

    @Override
    public Page<Patient> getPatient(Integer pageSize, Integer page) {
        return nursePatientRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Patient getPatient(Long id) {
        return nursePatientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient save(Patient patient) {
        return nursePatientRepository.save(patient);
    }

    ;

    @Override
    public List<Patient> getAllpatient() {
        return nursePatientRepository.findAll(Pageable.unpaged()).getContent();
    }

    ;

    @Override
    public Patient getPatientByAn(String an) {
        return nursePatientRepository.findPatientByAn(an);
    }

    @Override
    public List<Patient> getSearchedPatient(String name,String surname, String an) {
        return nursePatientRepository.findByNameIgnoreCaseContainingOrSurnameIgnoreCaseContainingOrAnIgnoreCaseContaining(name,surname,an);
    }
}

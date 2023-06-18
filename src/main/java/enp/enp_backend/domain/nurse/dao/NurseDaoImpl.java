package enp.enp_backend.domain.nurse.dao;


import enp.enp_backend.domain.nurse.JPArepository.NurseRepository;
import enp.enp_backend.domain.nurse.JPArepository.Nurse_PatientRepository;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("db")
@Repository
public class NurseDaoImpl implements NurseDao {

    @Autowired
    NurseRepository nurseRepository;

    @Override
    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    @Override
    public Nurse getNurse(Long id) {
        return nurseRepository.findById(id).orElse(null);
    }

    //----------------------------------
    @Autowired
    Nurse_PatientRepository nursePatientRepository;

    @Override
    public Integer getPatientSize(){ return Math.toIntExact(nursePatientRepository.count());}

    @Override
    public Page<Patient> getPatients(Integer pageSize, Integer page){
        return nursePatientRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    @Override
    public Patient getPatient(Long id){ return nursePatientRepository.findById(id).orElse(null); }

    @Override
    public Page<Patient> getPatient(Pageable pageRequest){ return nursePatientRepository.findAll(pageRequest); }

    @Override
    public Patient save(Patient patient){ return nursePatientRepository.save(patient);}

    @Override
    public Optional<Patient> findByID(Long id){ return nursePatientRepository.findById(id); }


}

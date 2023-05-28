package enp.enp_backend.dao;

import enp.enp_backend.entity.Patient;
import enp.enp_backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("db")
@Repository
public class PatientDaoImpl implements PatientDao{
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Integer getPatientSize(){ return Math.toIntExact(patientRepository.count());}

    @Override
    public Page<Patient> getPatients(Integer pageSize, Integer page){
        return patientRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    @Override
    public Patient getPatient(Long id){ return patientRepository.findById(id).orElse(null); }

    @Override
    public Page<Patient> getPatient(Pageable pageRequest){ return patientRepository.findAll(pageRequest); }

    @Override
    public Patient save(Patient patient){ return patientRepository.save(patient);}

    @Override
    public Optional<Patient> findByID(Long id){ return patientRepository.findById(id); }
}

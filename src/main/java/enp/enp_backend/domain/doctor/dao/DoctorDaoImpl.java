package enp.enp_backend.domain.doctor.dao;

import enp.enp_backend.domain.doctor.JPArepository.DoctorRepository;
import enp.enp_backend.domain.doctor.JPArepository.Doctor_PatientRepository;
import enp.enp_backend.entity.Doctor;
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
public class DoctorDaoImpl implements DoctorDao {
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    //----------------------------------
    @Autowired
    Doctor_PatientRepository doctorPatientRepository;

    @Override
    public Integer getPatientSize(){ return Math.toIntExact(doctorPatientRepository.count());}

    @Override
    public Page<Patient> getPatients(Integer pageSize, Integer page){
        return doctorPatientRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    @Override
    public Patient getPatient(Long id){ return doctorPatientRepository.findById(id).orElse(null); }

    @Override
    public Page<Patient> getPatient(Pageable pageRequest){ return doctorPatientRepository.findAll(pageRequest); }

    @Override
    public Patient save(Patient patient){ return doctorPatientRepository.save(patient);}

    @Override
    public Optional<Patient> findByID(Long id){ return doctorPatientRepository.findById(id); }


}

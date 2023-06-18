package enp.enp_backend.domain.doctor.service;


import enp.enp_backend.domain.doctor.dao.DoctorDao;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    DoctorDao doctorDao;

    @Override
    public Doctor save(Doctor doctor) {
        return doctorDao.save(doctor);
    }

    @Override
    public Doctor getDoctor(Long id) {
        return doctorDao.getDoctor(id);
    }


    //---------------------------------


    @Override
    public Integer getPatientSize(){ return doctorDao.getPatientSize(); }

    @Override
    public Page<Patient> getPatient(Integer pageSize, Integer page) { return doctorDao.getPatients(pageSize,page); }

    @Override
    public Patient getPatient(Long id){ return doctorDao.getPatient(id);}

    @Override
    public Patient save(Patient patient){ return doctorDao.save(patient);};

    @Override
    public List<Patient> getAllpatient(){ return doctorDao.getPatient(Pageable.unpaged()).getContent();};

}

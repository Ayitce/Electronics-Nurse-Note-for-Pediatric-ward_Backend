package enp.enp_backend.service;

import enp.enp_backend.dao.PatientDao;
import enp.enp_backend.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientDao patientDao;

    @Override
    public Integer getPatientSize(){ return patientDao.getPatientSize(); }

    @Override
    public Page<Patient> getPatient(Integer pageSize, Integer page) { return patientDao.getPatients(pageSize,page); }

    @Override
    public Patient getPatient(Long id){ return patientDao.getPatient(id);}

    @Override
    public Patient save(Patient patient){ return patientDao.save(patient);};

    @Override
    public List<Patient> getAllpatient(){ return patientDao.getPatient(Pageable.unpaged()).getContent();};
}

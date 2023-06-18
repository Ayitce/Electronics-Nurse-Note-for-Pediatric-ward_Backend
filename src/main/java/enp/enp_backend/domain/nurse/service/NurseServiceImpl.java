package enp.enp_backend.domain.nurse.service;

import enp.enp_backend.domain.nurse.dao.NurseDao;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService{
    @Autowired
    NurseDao nurseDao;

    @Override
    public Nurse save(Nurse nurse) {
        return nurseDao.save(nurse);
    }

    @Override
    public Nurse getNurse(Long id) {
        return nurseDao.getNurse(id);
    }

    //-----------------------

    @Override
    public Integer getPatientSize(){ return nurseDao.getPatientSize(); }

    @Override
    public Page<Patient> getPatient(Integer pageSize, Integer page) { return nurseDao.getPatients(pageSize,page); }

    @Override
    public Patient getPatient(Long id){ return nurseDao.getPatient(id);}

    @Override
    public Patient save(Patient patient){ return nurseDao.save(patient);};

    @Override
    public List<Patient> getAllpatient(){ return nurseDao.getPatient(Pageable.unpaged()).getContent();};

}

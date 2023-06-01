package enp.enp_backend.service;

import enp.enp_backend.dao.DoctorDao;
import enp.enp_backend.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

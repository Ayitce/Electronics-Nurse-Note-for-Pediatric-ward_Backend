package enp.enp_backend.dao;

import enp.enp_backend.entity.Doctor;

public interface DoctorDao {
    Doctor save(Doctor doctor);
    Doctor getDoctor(Long id);
}

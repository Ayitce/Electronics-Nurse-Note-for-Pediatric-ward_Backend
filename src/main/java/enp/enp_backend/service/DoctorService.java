package enp.enp_backend.service;

import enp.enp_backend.entity.Doctor;

public interface DoctorService {
    Doctor save(Doctor doctor);
    Doctor getDoctor(Long id);
}

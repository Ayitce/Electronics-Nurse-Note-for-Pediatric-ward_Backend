package enp.enp_backend.service;

import enp.enp_backend.entity.Nurse;

public interface NurseService {
    Nurse save(Nurse nurse);
    Nurse getNurse(Long id);
}

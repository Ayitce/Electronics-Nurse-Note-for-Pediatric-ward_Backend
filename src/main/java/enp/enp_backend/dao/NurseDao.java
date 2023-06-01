package enp.enp_backend.dao;

import enp.enp_backend.entity.Nurse;

public interface NurseDao {

    Nurse save(Nurse nurse);
    Nurse getNurse(Long id);
}

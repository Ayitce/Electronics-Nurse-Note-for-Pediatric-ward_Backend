package enp.enp_backend.service;

import enp.enp_backend.dao.NurseDao;
import enp.enp_backend.entity.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseServiceImpl implements NurseService {
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
}

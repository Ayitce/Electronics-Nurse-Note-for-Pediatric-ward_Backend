package enp.enp_backend.dao;


import enp.enp_backend.entity.Nurse;
import enp.enp_backend.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("db")
@Repository
public class NurseDaoImpl implements NurseDao {

    @Autowired
    NurseRepository nurseRepository;

    @Override
    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    @Override
    public Nurse getNurse(Long id) {
        return nurseRepository.findById(id).orElse(null);
    }
}

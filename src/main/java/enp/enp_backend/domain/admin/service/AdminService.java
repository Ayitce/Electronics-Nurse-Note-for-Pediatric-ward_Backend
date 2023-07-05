package enp.enp_backend.domain.admin.service;

import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.Patient;
import enp.enp_backend.entity.User;

import java.util.List;

public interface AdminService {
    User save(User user);
    List<User> getAllUser();
    Doctor save(Doctor doctor);
    Nurse save(Nurse nurse);

    List<User> getNurseUser();
    List<User> getDoctorUser();

    User getUser(Long id);
    List<User> getSearchedUser(String search);

}

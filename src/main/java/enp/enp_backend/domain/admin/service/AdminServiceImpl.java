package enp.enp_backend.domain.admin.service;

import enp.enp_backend.domain.admin.repository.jpa.Admin_DoctorRepository;
import enp.enp_backend.domain.admin.repository.jpa.Admin_NurseRepository;
import enp.enp_backend.domain.admin.repository.jpa.Admin_UserRepository;
import enp.enp_backend.entity.Doctor;
import enp.enp_backend.entity.Nurse;
import enp.enp_backend.entity.User;
import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    Admin_UserRepository admin_userRepository;

    @Autowired
    Admin_DoctorRepository admin_doctorRepository;

    @Autowired
    Admin_NurseRepository admin_nurseRepository;

    @Override
    public User save(User user) {
        return admin_userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return admin_userRepository.findAll(Pageable.unpaged()).getContent();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return admin_doctorRepository.save(doctor);
    }

    @Override
    public Nurse save(Nurse nurse) {
        return admin_nurseRepository.save(nurse);
    }


    @Override
    public User getUser(Long id) {
        return admin_userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getSearchedUser(String search){
        return admin_userRepository.findByUsernameIgnoreCaseContainingOrNurseNameIgnoreCaseContainingOrNurseSurnameIgnoreCaseContainingOrDoctorNameIgnoreCaseContainingOrDoctorSurnameIgnoreCaseContaining(search,search,search,search,search);
    }

}

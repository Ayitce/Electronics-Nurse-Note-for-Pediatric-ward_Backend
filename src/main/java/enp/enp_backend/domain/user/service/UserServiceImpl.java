package enp.enp_backend.domain.user.service;

import enp.enp_backend.entity.Admit;
import enp.enp_backend.entity.User;
import enp.enp_backend.domain.user.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }


}

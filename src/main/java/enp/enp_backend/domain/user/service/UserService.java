package enp.enp_backend.domain.user.service;

import enp.enp_backend.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Page<User> getUsers(Integer pageSize, Integer page);

    User getUser(Long id);
}

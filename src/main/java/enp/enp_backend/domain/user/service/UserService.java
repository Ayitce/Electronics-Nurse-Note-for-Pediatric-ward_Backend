package enp.enp_backend.domain.user.service;

import enp.enp_backend.security.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User save(User user);

    Page<User> getUsers(Integer pageSize, Integer page);

    User getUser(Long id);
}

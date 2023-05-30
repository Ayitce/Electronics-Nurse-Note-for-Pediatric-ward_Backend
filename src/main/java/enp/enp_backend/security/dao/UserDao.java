package enp.enp_backend.security.dao;

import enp.enp_backend.security.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserDao {
    User save(User user);

    Page<User> getUsers(Integer pageSize, Integer page);

    Optional<User> findByID(Long id);

    User getUsers(Long id);
}

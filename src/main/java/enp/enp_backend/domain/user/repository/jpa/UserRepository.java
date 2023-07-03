package enp.enp_backend.domain.user.repository.jpa;

import enp.enp_backend.entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Profile("db")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

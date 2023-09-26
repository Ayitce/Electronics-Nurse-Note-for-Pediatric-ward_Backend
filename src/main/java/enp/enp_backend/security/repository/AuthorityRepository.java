package enp.enp_backend.security.repository;


import enp.enp_backend.security.entity.Authority;
import enp.enp_backend.security.entity.AuthorityName;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findByName(AuthorityName input);
}

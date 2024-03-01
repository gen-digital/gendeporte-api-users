package cl.gendigital.gendeporte.users.infra.persistence.repository.jpa;

import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByValidationCode(String validationCode);

}

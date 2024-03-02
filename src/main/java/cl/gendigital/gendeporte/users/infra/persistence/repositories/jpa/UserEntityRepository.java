package cl.gendigital.gendeporte.users.infra.persistence.repositories.jpa;

import cl.gendigital.gendeporte.users.core.entities.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserEntityRepository extends PagingAndSortingRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByValidationCode(String validationCode);
}

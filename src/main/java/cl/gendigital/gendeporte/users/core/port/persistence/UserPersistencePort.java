package cl.gendigital.gendeporte.users.core.port.persistence;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserPersistencePort {
    Optional<UserPersistence> findByUsername(String username);

    /**
     * Save a user
     *
     * @param userPersistence UserPersistence
     * @return Integer
     */
    Integer save(UserPersistence userPersistence);

    Optional<UserPersistence> findByEmail(String email);

    UserPersistence moreInformation(UserPersistence user);

    UserPersistence verify(UserPersistence user, UserPersistence found);

}


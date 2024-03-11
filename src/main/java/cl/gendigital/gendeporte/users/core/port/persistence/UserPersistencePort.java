package cl.gendigital.gendeporte.users.core.port.persistence;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;

import java.util.Optional;

public interface UserPersistencePort {
    Optional<UserPersistence> findByUsername(String username);

    Integer save(UserPersistence userPersistence);

    Optional<UserPersistence> findByEmail(String email);
    boolean existByUsername(String user);

    boolean existByEmail(String email);

    UserPersistence verify(UserPersistence user, UserPersistence found);


}


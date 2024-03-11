package cl.gendigital.gendeporte.users.core.port.persistence;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;

import java.util.Optional;

public interface UserInfoPersistencePort {

    UserInfoPersistence moreInfo(UserInfoPersistence userInfoPersistence, UserInfoPersistence found);

    Optional<UserInfoPersistence> findByUser(User user);

}

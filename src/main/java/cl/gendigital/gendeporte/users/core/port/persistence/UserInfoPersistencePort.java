package cl.gendigital.gendeporte.users.core.port.persistence;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;


import java.util.Optional;

public interface UserInfoPersistencePort {

    Optional<UserInfoPersistence> findByUserId(Integer userId);

    UserInfoPersistence moreInfo(UserInfoPersistence userInfoPersistence, Integer userId);
}

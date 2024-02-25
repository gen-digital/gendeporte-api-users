package cl.gendigital.gendeporte.users.infra.utils.mapper;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PersistenceMapper {
    public static User persistenceToEntity(UserPersistence userPersistence) {
        User userEntity = new User();
        userEntity.setUsername(userPersistence.getUsername());
        userEntity.setEmail(userPersistence.getEmail());
        userEntity.setPassword(userPersistence.getPassword());
        userEntity.setCreatedAt(userPersistence.getCreatedAt());
        userEntity.setValidationCode(userPersistence.getValidationCode());
        userEntity.setEnabledAt(userPersistence.getEnabledAt());
        return userEntity;
    }

    public static UserPersistence entityToPersistence(User user) {
        var userPersistence =
                new UserPersistence(user.getUsername(), user.getEmail(), user.getPassword());
        userPersistence.setCreatedAt(user.getCreatedAt());
        userPersistence.setUpdatedAt(user.getUpdatedAt());
        userPersistence.setExpiredAt(user.getExpiredAt());
        userPersistence.setEnabledAt(user.getEnabledAt());
        userPersistence.setDisabledAt(user.getDisabledAt());
        userPersistence.setLockedAt(user.getLockedAt());
        return userPersistence;
    }
}

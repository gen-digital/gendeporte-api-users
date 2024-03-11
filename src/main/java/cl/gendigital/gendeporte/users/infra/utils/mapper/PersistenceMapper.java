package cl.gendigital.gendeporte.users.infra.utils.mapper;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.UserInfo;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PersistenceMapper {
    public static User persistenceToEntity(UserPersistence userPersistence) {
        User userEntity = new User();
        userEntity.setUsername(userPersistence.getUsername());
        userEntity.setEmail(userPersistence.getEmail());
        userEntity.setPassword(userPersistence.getPassword());

        userEntity.setValidationCode(userPersistence.getValidationCode());

        userEntity.setCreatedAt(userPersistence.getCreatedAt());
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

        userPersistence.setValidationCode(user.getValidationCode());
        return userPersistence;
    }

    public static UserInfo persistenceToEntity(UserInfoPersistence userInfoPersistence){
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userInfoPersistence.getFirstName());
        userInfo.setMiddleName(userInfoPersistence.getMiddleName());
        userInfo.setLastName(userInfoPersistence.getLastName());
        userInfo.setSecondLastName(userInfoPersistence.getSecondLastName());
        userInfo.setBirthdate(userInfoPersistence.getBirthdate());
        userInfo.setRut(userInfoPersistence.getRut());
        userInfo.setNationality(userInfoPersistence.getNationality());
        userInfo.setPhone(userInfoPersistence.getPhone());
        userInfo.setAddress(userInfoPersistence.getAddress());
        userInfo.setMaritalStatus(userInfoPersistence.getMaritalStatus());
        return userInfo;
    }

    public static UserInfoPersistence entityToPersistence(UserInfo userInfo){
        var userInfoPersistence =
                new UserInfoPersistence(userInfo.getFirstName(), userInfo.getMiddleName(), userInfo.getLastName(), userInfo.getSecondLastName(),
                        userInfo.getBirthdate(), userInfo.getRut(), userInfo.getNationality(), userInfo.getPhone(), userInfo.getAddress(), userInfo.getMaritalStatus());
        userInfoPersistence.setCreatedAt(userInfo.getCreatedAt());
        userInfoPersistence.setCreatedAt(userInfo.getCreatedAt());
        userInfoPersistence.setUpdatedAt(userInfo.getUpdatedAt());
        userInfoPersistence.setExpiredAt(userInfo.getExpiredAt());
        userInfoPersistence.setEnabledAt(userInfo.getEnabledAt());
        userInfoPersistence.setDisabledAt(userInfo.getDisabledAt());
        userInfoPersistence.setLockedAt(userInfo.getLockedAt());
        return userInfoPersistence;
    }


}

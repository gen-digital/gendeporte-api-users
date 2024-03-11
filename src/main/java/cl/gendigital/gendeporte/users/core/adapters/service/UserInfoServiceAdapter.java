package cl.gendigital.gendeporte.users.core.adapters.service;

import cl.gendigital.gendeporte.users.core.commands.user_info.EnrichCmd;
import cl.gendigital.gendeporte.users.core.commands.user_info.GetUserInfoCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.UserInfo;
import cl.gendigital.gendeporte.users.core.entities.persistence.PersistenceBase;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.UserNotExist;
import cl.gendigital.gendeporte.users.core.exceptions.user_info.persistence.UserInfoNotExist;
import cl.gendigital.gendeporte.users.core.exceptions.user_info.service.NoValidatedUser;
import cl.gendigital.gendeporte.users.core.port.persistence.UserInfoPersistencePort;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.core.port.services.UserInfoServicePort;
import cl.gendigital.gendeporte.users.infra.utils.mapper.PersistenceMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserInfoServiceAdapter implements UserInfoServicePort {

    private final UserInfoPersistencePort userInfoPersistencePort;
    private final UserPersistencePort userPersistencePort;

    private UserInfoPersistence toPersistence(EnrichCmd cmd) {
        return new UserInfoPersistence(
                cmd.getFirstName(),cmd.getMiddleName(),cmd.getLastName(),cmd.getSecondLastName(),cmd.getBirthDate(),cmd.getRut(),
                cmd.getNationality(),cmd.getPhone(),cmd.getAddress(),cmd.getMaritalStatus()
        );
    }
    @Override
    public UserInfo enrich(String username,EnrichCmd cmd) {
        var foundUser= userPersistencePort.id(username);
        var user = userPersistencePort.findByUsername(username)
                .orElseThrow(() -> new UserInfoNotExist(username));
        if (user.getEnabledAt() != null) {
            var enrichUser = userInfoPersistencePort.moreInfo(toPersistence(cmd), foundUser);
            return new UserInfo(enrichUser);
        }else {
            throw new NoValidatedUser();
        }
    }

    @Override
    public UserInfo getUserInfo(GetUserInfoCmd cmd) {
        var userId= userPersistencePort.id(cmd.getUsername());
        return userInfoPersistencePort
                .findByUserId(userId)
                .map(UserInfo::new)
                .orElseThrow(()-> new UserInfoNotExist(cmd.getUsername()));
    }
}

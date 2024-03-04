package cl.gendigital.gendeporte.users.core.adapters.service;

import cl.gendigital.gendeporte.users.core.commands.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.GetUserCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.core.exceptions.persistence.UserExist;
import cl.gendigital.gendeporte.users.core.exceptions.persistence.UserNotExist;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceAdapter implements UserServicePort {

    private final UserPersistencePort userPersistencePort;

    private UserPersistence toPersistence(CreateUserCmd cmd) {
        return new UserPersistence(
                cmd.getUsername(), cmd.getEmail(), cmd.getPassword());
    }

    @Override
    public Integer createUser(CreateUserCmd cmd) {
        if (userPersistencePort.existByUsername(cmd.getUsername())) {
            throw new UserExist("user", "username", cmd.getUsername());
        }
        if (userPersistencePort.existByEmail(cmd.getEmail())){
            throw new UserExist("user","email",cmd.getEmail());
        }
        return userPersistencePort.save(toPersistence(cmd));
    }

    @Override
    public User getUser(GetUserCmd cmd) {
        return userPersistencePort
                .findByUsername(cmd.getUsername())
                .map(User::new)
                .orElseThrow(()->new UserNotExist("user","username",cmd.getUsername()));
    }

}

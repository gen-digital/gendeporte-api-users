package cl.gendigital.gendeporte.users.core.adapters.service;

import cl.gendigital.gendeporte.users.core.commands.user.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.GetUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.EnrichCmd;
import cl.gendigital.gendeporte.users.core.commands.user.VerificationCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.core.exceptions.user.service.MismachedValidationCode;
import cl.gendigital.gendeporte.users.core.exceptions.user.service.NoValidatedUser;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.UserExist;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.UserNotExist;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserServiceAdapter implements UserServicePort {

    private final UserPersistencePort userPersistencePort;

    private UserPersistence toPersistence(CreateUserCmd cmd) {
        return new UserPersistence(
                cmd.getUsername(), cmd.getEmail(), cmd.getPassword());
    }

    private UserPersistence toPersistance(VerificationCmd cmd) {
        return new UserPersistence(cmd.getUsername(), cmd.getValidationCode());
    }

    private UserPersistence toPersistance(EnrichCmd cmd) {
        return new UserPersistence(cmd.getUsername(), cmd.getFirstName(), cmd.getLastName(), cmd.getPhone(), cmd.getAddress());
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

    @Override
    public User verifyUser(VerificationCmd cmd) {
        var foundUser =
                userPersistencePort
                        .findByUsername(cmd.getUsername())
                        .orElseThrow(()->new UserNotExist("user","username",cmd.getUsername()));
        if (cmd.getValidationCode().equals(foundUser.getValidationCode())) {
            var verifiedUser = userPersistencePort.verify(toPersistance(cmd), foundUser);
            verifiedUser.setEnabledAt(LocalDateTime.now());
            return new User(verifiedUser);
        } else {
            throw new MismachedValidationCode();
        }
    }

    @Override
    public User enrich(EnrichCmd cmd) {
        var foundUser =
                userPersistencePort
                        .findByUsername(cmd.getUsername())
                        .orElseThrow(()->new UserNotExist("user","username",cmd.getUsername()));
        if (foundUser.getEnabledAt()!= null) {
            var userInfo = userPersistencePort.enrich(toPersistance(cmd));
            return new User(userInfo);
        }
        throw new NoValidatedUser();
    }
}

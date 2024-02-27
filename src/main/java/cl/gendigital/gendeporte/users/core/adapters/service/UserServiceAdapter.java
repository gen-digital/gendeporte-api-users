package cl.gendigital.gendeporte.users.core.adapters.service;

import cl.gendigital.gendeporte.users.core.commands.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.GetUserCmd;
import cl.gendigital.gendeporte.users.core.commands.MoreInformationUserCmd;
import cl.gendigital.gendeporte.users.core.commands.VerifyUserCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceAdapter implements UserServicePort {

    private final UserPersistencePort userPersistencePort;

    private UserPersistence toPersistence(CreateUserCmd cmd) {
        var userPersistence =
                new UserPersistence(
                        cmd.getUsername(), cmd.getEmail(), cmd.getPassword());
        return userPersistence;
    }

    private UserPersistence toPersistance(VerifyUserCmd cmd) {
        return new UserPersistence(cmd.getUsername(), cmd.getValidationCode());
    }

    private UserPersistence toPersistance(MoreInformationUserCmd cmd) {
        return new UserPersistence(cmd.getUsername(), cmd.getFirstName(), cmd.getLastName(), cmd.getPhone(), cmd.getAddress());
    }

    @Override
    public Integer createUser(CreateUserCmd cmd) {
        if (userPersistencePort.findByUsername(cmd.getUsername()).isPresent()) {
            return null;
        } else {
            return userPersistencePort.save(toPersistence(cmd));
        }
    }

    @Override
    public User getUser(GetUserCmd cmd) {
        return userPersistencePort
                .findByUsername(cmd.getUsername())
                .map(User::new)
                .orElse(null);
    }

    @Override
    public User verifyUser(VerifyUserCmd cmd) {
        var foundUser =
                userPersistencePort
                        .findByUsername(cmd.getUsername())
                        .orElseThrow(() -> null);
        if (cmd.getValidationCode().equals(foundUser.getValidationCode())) {
            var verifiedUser = userPersistencePort.verify(toPersistance(cmd), foundUser);
            return new User(verifiedUser);
        } else {
            return null;
        }
    }

    @Override
    public User moreInfo(MoreInformationUserCmd cmd) {
        var foundUser =
                userPersistencePort
                        .findByUsername(cmd.getUsername())
                        .orElseThrow(() -> null);
        if (userPersistencePort.findByUsername(cmd.getUsername()).isPresent() && foundUser.getEnabledAt()!= null) {
            var userInfo = userPersistencePort.moreInformation(toPersistance(cmd));
            return new User(userInfo);
        }
        return null;
    }
}

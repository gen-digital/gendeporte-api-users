package cl.gendigital.gendeporte.users.core.port.services;

import cl.gendigital.gendeporte.users.core.commands.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.GetUserCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;

import java.util.Optional;

public interface UserServicePort {

    Integer createUser(CreateUserCmd cmd);
    User getUser(GetUserCmd cmd);
}

package cl.gendigital.gendeporte.users.core.port.services;

import cl.gendigital.gendeporte.users.core.commands.user.CreateUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.GetUserCmd;
import cl.gendigital.gendeporte.users.core.commands.user.VerificationCmd;
import cl.gendigital.gendeporte.users.core.entities.domain.user.User;

public interface UserServicePort {

    Integer createUser(CreateUserCmd cmd);
    User getUser(GetUserCmd cmd);
    User verifyUser(String username,VerificationCmd cmd);

}

package cl.gendigital.gendeporte.users.core.commands.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateUserCmd {
    private final String username;
    private final String password;
    private final String email;
}

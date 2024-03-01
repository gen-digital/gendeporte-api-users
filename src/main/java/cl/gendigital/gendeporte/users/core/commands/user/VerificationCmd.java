package cl.gendigital.gendeporte.users.core.commands.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VerificationCmd {
    private final String username;
    private final String validationCode;
}

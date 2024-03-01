package cl.gendigital.gendeporte.users.core.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class VerifyUserCmd {
    private final String username;
    private final String validationCode;
}

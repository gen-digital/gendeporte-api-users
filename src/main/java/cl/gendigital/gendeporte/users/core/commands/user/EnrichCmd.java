package cl.gendigital.gendeporte.users.core.commands.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EnrichCmd {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String address;
}

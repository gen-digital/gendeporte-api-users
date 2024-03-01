package cl.gendigital.gendeporte.users.core.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MoreInformationUserCmd {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String address;
}

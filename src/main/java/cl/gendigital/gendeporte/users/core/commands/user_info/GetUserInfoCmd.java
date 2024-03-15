package cl.gendigital.gendeporte.users.core.commands.user_info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GetUserInfoCmd {
    private final String username;
}

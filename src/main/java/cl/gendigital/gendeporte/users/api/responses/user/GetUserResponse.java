package cl.gendigital.gendeporte.users.api.responses.user;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class UserResponse {
    private final String username;
    private final String email;
}

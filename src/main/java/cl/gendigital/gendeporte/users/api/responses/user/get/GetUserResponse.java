package cl.gendigital.gendeporte.users.api.responses.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserResponse {
    private String username;
    private String email;
}

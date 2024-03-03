package cl.gendigital.gendeporte.users.api.responses.user.get;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetUserResponse {
    private String username;
    private String email;
    private String validation_code;
    private LocalDateTime created_at;
    private LocalDateTime enabled_at;
    private LocalDateTime updated_at;
    private String address;
}

package cl.gendigital.gendeporte.users.api.responses.user.get;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetUserResponse {
    private String username;
    private String email;
    private String validationCode;
    private LocalDateTime createdAt;
    private LocalDateTime enabledAt;
    private LocalDateTime updatedAt;
    private String address;
}

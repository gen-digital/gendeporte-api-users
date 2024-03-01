package cl.gendigital.gendeporte.users.api.responses.user.post;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class PostVerificationResponse {
    private final String username;
    private final LocalDateTime enabledAt;
}

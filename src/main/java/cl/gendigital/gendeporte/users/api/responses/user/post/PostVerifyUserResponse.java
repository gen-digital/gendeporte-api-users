package cl.gendigital.gendeporte.users.api.responses.user.post;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostVerifyUserResponse {
    private final String username;
    private final LocalDateTime createdAt;
}

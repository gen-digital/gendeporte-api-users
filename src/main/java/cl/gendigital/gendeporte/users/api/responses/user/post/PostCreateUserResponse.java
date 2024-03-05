package cl.gendigital.gendeporte.users.api.responses.user.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateUserResponse {
    @JsonProperty(value="user_id",access= JsonProperty.Access.READ_ONLY)
    private final Integer userId;
}


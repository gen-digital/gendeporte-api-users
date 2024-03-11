package cl.gendigital.gendeporte.users.api.responses.user.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetUserResponse {
    private String username;
    private String email;
    private String password;
    @JsonProperty(value="validation_code",access= JsonProperty.Access.READ_ONLY)
    private String validationCode;
    @JsonProperty(value="created_at",access= JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(value="enabled_at",access= JsonProperty.Access.READ_ONLY)
    private LocalDateTime enabledAt;
    @JsonProperty(value="update_at",access= JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

}

package cl.gendigital.gendeporte.users.api.responses.user.patch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class PatchEnrichResponse {
    private final String username;
    @JsonProperty(value="updated_at",access= JsonProperty.Access.READ_ONLY)
    private final LocalDateTime uptatedAt;
}

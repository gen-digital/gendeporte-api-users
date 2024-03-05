package cl.gendigital.gendeporte.users.api.request.user.patch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchEnrichRequest {
    @NotBlank
    @Size(min=4,max = 100)
    private String username;
    @JsonProperty(value="first_name",access= JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min=1,max = 100)
    private String firstName;
    @JsonProperty(value="last_name",access= JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min=1,max = 100)
    private String lastName;
    @NotBlank
    @Size(min=1,max = 50)
    private String phone;
    @NotBlank
    @Size(min=1,max = 100)
    private String address;
}

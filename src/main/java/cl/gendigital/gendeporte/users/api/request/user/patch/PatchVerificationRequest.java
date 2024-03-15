package cl.gendigital.gendeporte.users.api.request.user.patch;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchVerificationRequest {
    @JsonProperty("validation_code")
    @NotBlank
    @Size(min=8,max = 14)
    private String validationCode;
}

package cl.gendigital.gendeporte.users.api.request.user.get;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRequest {
    @NotBlank
    @Size(min=4,max = 100)
    private String username;
}

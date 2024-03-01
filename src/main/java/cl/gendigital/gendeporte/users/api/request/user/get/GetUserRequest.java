package cl.gendigital.gendeporte.users.api.request.user.get;

import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRequest {
    @NotEmpty
    @Size(min=4,max = 100)
    private String username;
}

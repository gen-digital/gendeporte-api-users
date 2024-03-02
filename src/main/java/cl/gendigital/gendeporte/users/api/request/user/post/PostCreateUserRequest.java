package cl.gendigital.gendeporte.users.api.request.user.post;

import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateUserRequest {

    @NotEmpty
    @Size(min=4,max = 100)
    private String username;
    @NotEmpty
    @Size(min=4,max = 100)
    private String password;
    @NotEmpty
    @Size(min=4,max = 100)
    private String email;
    
}
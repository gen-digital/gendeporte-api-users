package cl.gendigital.gendeporte.users.api.request.user.post;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateUserRequest {


    @NotBlank
    @Size(min=4,max = 100)
    private String username;
    @NotBlank
    @Size(min=4,max = 100)
    private String password;
    @NotBlank
    @Size(min=4,max = 100)
    private String email;
    
}
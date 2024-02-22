package cl.gendigital.gendeporte.users.api.request.user;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostNewUserRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;
}

package cl.gendigital.gendeporte.users.api.request.user.post;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;


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

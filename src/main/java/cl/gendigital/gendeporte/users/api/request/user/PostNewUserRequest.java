package cl.gendigital.gendeporte.users.api.request.user;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostNewUserRequest {

    private String username;

    private String password;

    private String email;
}

package cl.gendigital.gendeporte.users.api.request.user.post;


import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVerifyUserRequest {
    @NotNull
    private String username;
    @NotNull
    private String validationCode;

}

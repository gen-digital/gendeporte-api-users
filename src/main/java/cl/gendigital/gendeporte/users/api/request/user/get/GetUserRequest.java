package cl.gendigital.gendeporte.users.api.request.user.get;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRequest {
    @NotNull
    private String username;
}

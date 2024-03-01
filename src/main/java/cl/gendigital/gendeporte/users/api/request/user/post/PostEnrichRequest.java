package cl.gendigital.gendeporte.users.api.request.user.post;

import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEnrichRequest {
    @NotEmpty
    @Size(min=4,max = 100)
    private String username;
    @NotEmpty
    @Size(min=1,max = 100)
    private String firstName;
    @NotEmpty
    @Size(min=1,max = 100)
    private String lastName;
    @NotEmpty
    @Size(min=1,max = 50)
    private String phone;
    @NotEmpty
    @Size(min=1,max = 100)
    private String address;
}

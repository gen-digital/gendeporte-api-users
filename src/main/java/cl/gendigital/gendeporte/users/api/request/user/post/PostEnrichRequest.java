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
    private String first_name;
    @NotEmpty
    @Size(min=1,max = 100)
    private String last_name;
    @NotEmpty
    @Size(min=1,max = 50)
    private String phone;
    @NotEmpty
    @Size(min=1,max = 100)
    private String address;
}

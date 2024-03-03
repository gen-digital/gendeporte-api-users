package cl.gendigital.gendeporte.users.api.request.user.post;


import lombok.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVerificationRequest {
    @NotEmpty
    @Size(min=4,max = 100)
    private String username;
    @NotEmpty
    @Size(min=8,max = 14)
    private String validation_code;

}

package cl.gendigital.gendeporte.users.api.request.user_info.patch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchEnrichRequest {
    @NotBlank
    @Size(min=4,max = 100)
    @JsonProperty("first_name")
    private String firstName;
    @NotBlank
    @Size(min=4,max = 100)
    @JsonProperty("middle_name")
    private String middleName;
    @NotBlank
    @Size(min=4,max = 100)
    @JsonProperty("last_name")
    private String lastName;
    @NotBlank
    @JsonProperty("sacond_last_name")
    @Size(min=4,max = 100)
    private String secondLastName;
    @NotBlank
    @JsonProperty("birth_date")
    @Size(min=4,max = 100)
    private LocalDate birthdate;
    @NotBlank
    @Size(min=4,max = 100)
    private String rut;
    @NotBlank
    @Size(min=4,max = 100)
    private String nationality;
    @NotBlank
    @Size(min=4,max = 100)
    private String phone;
    @NotBlank
    @Size(min=4,max = 100)
    private String address;
    @NotBlank
    @JsonProperty("marital_status")
    @Size(min=4,max = 100)
    private String maritalStatus;
}

package cl.gendigital.gendeporte.users.api.responses.user_info.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
public class GetUserInfoResponse {
    @Size(min=4,max = 100)
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("sacond_last_name")
    private String secondLastName;
    @JsonProperty("birth_date")
    private LocalDate birthdate;
    private String rut;
    private String nationality;
    private String phone;
    private String address;
    @JsonProperty("marital_status")
    private String maritalStatus;
}

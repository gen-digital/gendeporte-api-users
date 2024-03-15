package cl.gendigital.gendeporte.users.api.responses.user_info.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class GetUserInfoResponse {
    @JsonProperty("first_name")
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

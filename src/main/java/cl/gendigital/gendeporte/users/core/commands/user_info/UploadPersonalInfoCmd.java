package cl.gendigital.gendeporte.users.core.commands.user_info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class UploadPersonalInfoCmd {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String secondLastName;
    private final LocalDate birthDate;
    private final String rut;
    private final String nationality;
    private final String phone;
    private final String address;
    private final String maritalStatus;


}

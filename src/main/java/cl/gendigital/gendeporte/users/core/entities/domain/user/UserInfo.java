package cl.gendigital.gendeporte.users.core.entities.domain.user;

import cl.gendigital.gendeporte.users.core.entities.domain.DomainBase;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Getter
@RequiredArgsConstructor
public class UserInfo extends DomainBase {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String secondLastName;
    private final LocalDate birthdate;
    private final String rut;
    private final String nationality;
    private final String phone;
    private final String address;
    private final String maritalStatus;

    public UserInfo(UserInfoPersistence userInfoPersistence){
        this.firstName = userInfoPersistence.getFirstName();
        this.middleName = userInfoPersistence.getMiddleName();
        this.lastName = userInfoPersistence.getLastName();
        this.secondLastName = userInfoPersistence.getSecondLastName();
        this.birthdate = userInfoPersistence.getBirthdate();
        this.rut = userInfoPersistence.getRut();
        this.nationality = userInfoPersistence.getNationality();
        this.phone = userInfoPersistence.getPhone();
        this.address = userInfoPersistence.getAddress();
        this.maritalStatus = userInfoPersistence.getMaritalStatus();
    }
}

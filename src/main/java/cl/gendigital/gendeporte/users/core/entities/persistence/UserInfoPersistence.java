package cl.gendigital.gendeporte.users.core.entities.persistence;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfoPersistence extends PersistenceBase{
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthdate;
    private String rut;
    private String nationality;
    private String phone;
    private String address;
    private String maritalStatus;

    public UserInfoPersistence(String firstName, String middleName, String lastName, String secondLastName, LocalDate birthdate, String rut,
                               String nationality, String phone, String address, String maritalStatus){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthdate = birthdate;
        this.rut = rut;
        this.nationality = nationality;
        this.phone = phone;
        this.address = address;
        this.maritalStatus = maritalStatus;
    }

    public UserInfoPersistence merge(UserInfoPersistence another){
        return new UserInfoPersistence(
                another.getFirstName() != null ? another.getFirstName() : getFirstName(),
                another.getMiddleName() != null ? another.getMiddleName() : getMiddleName(),
                another.getLastName() != null ? another.getLastName() : getLastName(),
                another.getSecondLastName() != null ? another.getSecondLastName() : getSecondLastName(),
                another.getBirthdate() != null ? another.getBirthdate() : getBirthdate(),
                another.getRut() != null ? another.getRut() : getRut(),
                another.getNationality() != null ? another.getNationality() : getNationality(),
                another.getPhone() != null ? another.getPhone() : getPhone(),
                another.getAddress() != null ? another.getAddress() : getAddress(),
                another.getMaritalStatus() != null ? another.getMaritalStatus() : getMaritalStatus()
        );
    }
}
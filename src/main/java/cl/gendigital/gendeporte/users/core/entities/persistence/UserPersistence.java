package cl.gendigital.gendeporte.users.core.entities.persistence;


import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UserPersistence extends PersistenceBase{

    private String username;
    private String email;
    private String password;
    private String validationCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public UserPersistence(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserPersistence(String username,String validationCode){
        this.username = username;
        this.validationCode = validationCode;
    }
    public UserPersistence(String username,String firstName,String lastName,String phone,String address){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public UserPersistence merge(UserPersistence another) {
        return new UserPersistence(
                another.getUsername() != null ? another.getUsername() : getUsername(),
                another.getEmail() != null ? another.getEmail() : getEmail(),
                another.getPassword() != null ? another.getPassword() : getPassword()
        );
    }
}

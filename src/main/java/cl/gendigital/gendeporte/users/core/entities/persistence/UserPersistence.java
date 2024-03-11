package cl.gendigital.gendeporte.users.core.entities.persistence;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPersistence extends PersistenceBase{

    private String username;
    private String email;
    private String password;
    private String validationCode;

    public UserPersistence(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserPersistence(String username,String validationCode){
        this.username = username;
        this.validationCode = validationCode;
    }

    public UserPersistence merge(UserPersistence another) {
        return new UserPersistence(
                another.getUsername() != null ? another.getUsername() : getUsername(),
                another.getEmail() != null ? another.getEmail() : getEmail(),
                another.getPassword() != null ? another.getPassword() : getPassword()
        );
    }
}

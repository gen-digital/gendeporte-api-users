package cl.gendigital.gendeporte.users.core.exceptions.user.persistence;

public class UserExist extends RuntimeException {

    public UserExist(String username){
        super("User with that username exist: " + username);
    }
}

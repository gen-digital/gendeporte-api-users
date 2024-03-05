package cl.gendigital.gendeporte.users.core.exceptions.user.persistence;

public class UserNotExist extends RuntimeException{
    public UserNotExist(String username){
        super("User with that username not exist: "+ username);
    }
}

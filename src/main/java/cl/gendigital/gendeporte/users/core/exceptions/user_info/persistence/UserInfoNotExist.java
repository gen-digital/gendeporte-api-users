package cl.gendigital.gendeporte.users.core.exceptions.user_info.persistence;

public class UserInfoNotExist extends RuntimeException{
    public UserInfoNotExist(String username){
        super("User with that username not exist: "+ username);
    }
}

package cl.gendigital.gendeporte.users.core.exceptions.user.persistence;

import java.util.Set;
import java.util.stream.Collectors;

public class UserNotExist extends RuntimeException{
    public UserNotExist(String username){
        super("User with that username not exist: "+ username);
    }
}

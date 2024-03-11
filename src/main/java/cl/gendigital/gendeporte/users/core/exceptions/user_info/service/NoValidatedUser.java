package cl.gendigital.gendeporte.users.core.exceptions.user_info.service;


public class NoValidatedUser extends RuntimeException {
    public NoValidatedUser(){super("The user is not validated");}
}

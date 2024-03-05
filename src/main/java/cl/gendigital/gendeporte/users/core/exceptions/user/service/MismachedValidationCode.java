package cl.gendigital.gendeporte.users.core.exceptions.user.service;

public class MismachedValidationCode extends RuntimeException{
    public MismachedValidationCode(){ super("The validation code does not match"); }

}

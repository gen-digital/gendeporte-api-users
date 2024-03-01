package cl.gendigital.gendeporte.users.core.exceptions.user.service;

public class MismachedValidationCode extends RuntimeException{
    public static final String MSG = "The validation code does not match";

    public MismachedValidationCode(){ super(MSG); }

    @Override
    public String toString(){ return getMessage(); }
}

package cl.gendigital.gendeporte.users.core.exceptions.user.persistence;

public class EmailExist extends RuntimeException{
    public EmailExist(String email){
        super("Email not valid : "+ email);
    }
}

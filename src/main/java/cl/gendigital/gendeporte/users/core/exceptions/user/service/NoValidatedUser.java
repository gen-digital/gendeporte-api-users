package cl.gendigital.gendeporte.users.core.exceptions.user.service;

import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.PersistenceException;

public class NoValidatedUser extends RuntimeException {
    public static final String MSG = "The user is not validated";

    public NoValidatedUser(){super(MSG);}
    @Override
    public String toString() {
        return getMessage();
    }
}

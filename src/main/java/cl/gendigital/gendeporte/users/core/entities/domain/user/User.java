package cl.gendigital.gendeporte.users.core.entities.domain.user;


import cl.gendigital.gendeporte.users.core.entities.domain.DomainBase;
import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Getter
@RequiredArgsConstructor
public class User extends DomainBase {

    private final String username;
    private final String email;
    private final String password;
    private final String validationCode;
    public User(UserPersistence userPersistence) {
        this.username = userPersistence.getUsername();
        this.email = userPersistence.getEmail();
        this.password = userPersistence.getPassword();
        this.validationCode = userPersistence.getValidationCode();

        this.createdAt = userPersistence.getCreatedAt();
        this.updatedAt = userPersistence.getUpdatedAt();
        this.expiredAt = userPersistence.getExpiredAt();
        this.lockedAt = userPersistence.getLockedAt();
        this.enabledAt = userPersistence.getEnabledAt();
        this.disabledAt = userPersistence.getDisabledAt();
    }

    public boolean isExpired() {
        return expiredAt != null && expiredAt.isBefore(LocalDateTime.now());
    }


    public boolean isLocked() {
        return lockedAt != null && lockedAt.isBefore(LocalDateTime.now());
    }


    public boolean isEnabled() {
        return enabledAt != null && enabledAt.isBefore(LocalDateTime.now());
    }

}

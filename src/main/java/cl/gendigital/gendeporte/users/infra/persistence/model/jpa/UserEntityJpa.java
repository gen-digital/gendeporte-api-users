package cl.gendigital.gendeporte.users.infra.persistence.model.jpa;

import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common.EntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntityJpa extends EntityBase {
    private static final long serialVersionUID = -5731260521355518321L;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "last_password", length = 100)
    private String lastPassword;

    @Column(name = "password_reset_at")
    private LocalDateTime passwordResetAt;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "validation_code", length = 100)
    private String validationCode;

    public String getFullname() {
        return getUsername().concat(" (").concat(getEmail()).concat(")");
    }

    public String getName() {
        return getUsername();
    }
}

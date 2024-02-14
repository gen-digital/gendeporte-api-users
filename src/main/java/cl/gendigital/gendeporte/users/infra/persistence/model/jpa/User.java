package cl.gendigital.gendeporte.users.infra.persistence.model.jpa;


import cl.gendigital.gendeporte.users.commons.DateUtils;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common.EntityBase;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends EntityBase {
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

    public boolean mustChangePassword() {
        return this.passwordResetAt != null && DateUtils.beforeNow(this.passwordResetAt);
    }
    /*public void add(Assignment assignment) {
        assignments.add(assignment);
    }*/

    /*public List<Role> getRoles() {
        return assignments
                .stream()
                .map(Assignment::getRole)
                .collect(Collectors.toList());
    }*/

    public String getFullname() {
        return getUsername().concat(" (").concat(getEmail()).concat(")");
    }

    @Override
    public String getName() {
        return getUsername();
    }
    }

package cl.gendigital.gendeporte.users.infra.persistence.model.jpa;

import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common.EntityBase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "users_info")
public class UserInfo extends EntityBase {

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "second_last_name", length = 100)
    private String secondLastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "rut", length = 8, unique = true)
    private String rut;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "phone", length = 100)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "marital_status", length = 100)
    private String maritalStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true, nullable = false
            , foreignKey = @ForeignKey(name = "fk_user_info_user", value = ConstraintMode.PROVIDER_DEFAULT))
    private User user;
    @Override
    public String getName() {
        return null;
    }
}

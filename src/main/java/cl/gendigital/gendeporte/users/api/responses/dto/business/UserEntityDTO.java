package cl.gendigital.gendeporte.users.api.responses.dto.business;

import cl.gendigital.gendeporte.users.api.responses.dto.BaseDTO;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.UserEntityJpa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntityDTO extends BaseDTO<UserEntityJpa> {
    private String username;
    private String email;
    private List<String> autorities;
}
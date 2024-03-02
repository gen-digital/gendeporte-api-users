package cl.gendigital.gendeporte.users.api.responses.dto;

import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common.EntityBase;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class BaseDTO <E extends EntityBase> {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime enabledAt;
    private LocalDateTime disabledAt;
    private LocalDateTime expiredAt;
    private LocalDateTime lockedAt;
}
package cl.gendigital.gendeporte.users.api.request;

import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common.EntityBase;

import java.io.Serializable;

public class EntityForm <E extends EntityBase> implements Serializable {
    private static final long serialVersionUID = -7143522747353211278L;
    //TODO ocultar para swagger
    protected Integer id;
    protected String code;
}
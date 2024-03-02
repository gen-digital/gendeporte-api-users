package cl.gendigital.gendeporte.users.infra.adapters.services.response;

import cl.gendigital.gendeporte.users.infra.adapters.services.common.BaseService;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResult<T> {
    private BaseService.Status status;
    private String message;
    private Throwable throwable;
    private T result;
}

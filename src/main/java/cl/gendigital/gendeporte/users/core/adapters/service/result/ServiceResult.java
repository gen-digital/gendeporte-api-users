package cl.gendigital.gendeporte.users.core.adapters.service.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResult<T> {
    //private BaseService.Status status;
    private String message;
    private Throwable throwable;
    private T result;
}

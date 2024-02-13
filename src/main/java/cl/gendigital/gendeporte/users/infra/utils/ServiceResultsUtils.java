package cl.gendigital.gendeporte.users.infra.utils;

import cl.gendigital.gendeporte.users.infra.adapters.services.common.BaseService;
import cl.gendigital.gendeporte.users.infra.adapters.services.response.ServiceResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceResultsUtils {

    public static boolean isOk(ServiceResult<?> serviceResult) {
        return serviceResult.getStatus().equals(BaseService.Status.OK);
    }
}

package cl.gendigital.gendeporte.users.infra.adapters.services.common;

import cl.gendigital.gendeporte.users.api.request.EntityForm;
import cl.gendigital.gendeporte.users.infra.adapters.services.response.ServiceResult;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common.EntityBase;
import org.springframework.data.domain.Page;
import cl.gendigital.gendeporte.users.infra.utils.ServiceResultsUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends EntityBase, F extends EntityForm<E>> {

    enum Status { OK, NOK }

    ServiceResult<List<E>> list();
    ServiceResult<Page<E>> findAll(Integer pageNumber, Integer pageSize, String filters, String sort);

    ServiceResult<E> findById(Integer id);
    ServiceResult<E> findByCode(String id);
    ServiceResult<E> save(F form);
    ServiceResult<E> update(E entity, F form);
    ServiceResult<E> delete(E entity);

    boolean existsById(Integer id);

    E enable(E entity);
    E disable(E entity);
    E expire(E entity);
    E lock(E entity);

    Class<E> getEntityClass();

    default void setEnabledAt(E entity) {
        setEnabledAt(entity, LocalDateTime.now());
    }

    default void setDisabledAt(E entity) {
        setDisabledAt(entity, LocalDateTime.now());
    }

    default void setExpiredAt(E entity) {
        setExpiredAt(entity, LocalDateTime.now());
    }

    default void setLockedAt(E entity) {
        setLockedAt(entity, LocalDateTime.now());
    }

    default void setEnabledAt(E entity, LocalDateTime date) {
        validateEnable(entity);
        entity.setEnabledAt(date);
    }

    default void setDisabledAt(E entity, LocalDateTime date) {
        validateDisable(entity);
        entity.setDisabledAt(date);
    }

    default void setExpiredAt(E entity, LocalDateTime date) {
        validateExpire(entity);
        entity.setExpiredAt(date);
    }

    default void setLockedAt(E entity, LocalDateTime date) {
        validateLock(entity);
        entity.setLockedAt(date);
    }

    default void validateSave(F form) {}
    default void validateUpdate(E entity, F form) {}
    default void validateDelete(E entity) {}
    default void validateEnable(E entity) {}
    default void validateDisable(E entity) {}
    default void validateExpire(E entity) {}
    default void validateLock(E entity) {}

    default boolean isOk(ServiceResult<?> serviceResult) {
        return ServiceResultsUtils.isOk(serviceResult);
    }

    default ServiceResult<E> createServiceResult(Optional<E> optional, String messageOk, String messageNok) {
        final boolean isPresent = optional.isPresent();
        return createServiceResult(optional.orElse(null), isPresent ? messageOk : messageNok, isPresent, null);
    }

    default <T> ServiceResult<T> createServiceResultOk(T result, String message) {
        return createServiceResult(result, message, true, null);
    }

    default <T> ServiceResult<T> createServiceResultNok(T result, String message) {
        return createServiceResult(result, message, false, null);
    }

    default <T> ServiceResult<T> createServiceResultNokWithThrowable(String message, Throwable throwable) {
        return createServiceResult(null, message, false, throwable);
    }

    default <T> ServiceResult<T> createServiceResultNokWithThrowable(T result, String message, Throwable throwable) {
        return createServiceResult(result, message, false, throwable);
    }

    default <T> ServiceResult<T> createServiceResult(T result, String message, boolean isOk, Throwable throwable) {
        return ServiceResult.<T>builder()
                .result(result)
                .message(message)
                .status(isOk ? Status.OK : Status.NOK)
                .throwable(throwable)
                .build();
    }
}

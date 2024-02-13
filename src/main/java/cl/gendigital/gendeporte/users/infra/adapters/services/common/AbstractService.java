package cl.gendigital.gendeporte.users.infra.adapters.services.common;

import cl.gendigital.gendeporte.users.api.request.EntityForm;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.common.EntityBase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class AbstractService <E extends EntityBase, F extends EntityForm<E>> implements BaseService<E, F> {
    protected static final String BASE_SERVICE = "baseService";

    protected final PagingAndSortingRepository<E, Integer> repository;
    protected MessageUtils messageUtils;
    protected ApplicationEventPublisher eventPublisher;
    protected PageRequestBuilder pageRequestBuilder;
    private ServiceFactory serviceFactory;
    private PaginationProperties paginationProperties;

    @Autowired
    public void setPaginationProperties(PaginationProperties paginationProperties) {
        this.paginationProperties = paginationProperties;
    }

    @Autowired
    public void setMessageUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Autowired
    public void setPageRequestBuilder(PageRequestBuilder pageRequestBuilder) {
        this.pageRequestBuilder = pageRequestBuilder;
    }

    @Autowired
    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public ServiceResult<List<E>> list() {
        return createServiceResultOk(
                (List<E>) repository.findAll()
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }

    @Override
    public ServiceResult<E> findById(Integer id) {
        return createServiceResult(
                repository.findById(id)
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET, id)
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_NOK, MessageUtils.DEFAULT_OFFSET, id)
        );
    }

    @Override
    public ServiceResult<E> findByCode(String id) {
        throw new UnsupportedOperationException("You must implement this feature in the functionally required concrete service");
    }

    @Override
    public ServiceResult<E> save(F form) {
        validateSave(form);
        return createServiceResultOk(
                saveEntity(form)
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }

    @Override
    public ServiceResult<E> update(E entity, F form) {
        validateUpdate(entity, form);
        return createServiceResultOk(
                updateEntity(entity, form)
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }

    @Override
    public ServiceResult<E> delete(E entity) {
        validateDelete(entity);
        return createServiceResultOk(
                deleteEntity(entity)
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        return (Class<E>) getTypeVariable()[0];
    }

    private Type[] getTypeVariable() {
        return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
    }

    @SuppressWarnings("unchecked")
    public Class<F> getEntityForm() {
        return (Class<F>) getTypeVariable()[1];
    }

    @SuppressWarnings("unchecked")
    private E saveEntity(F form) {
        E entity = (E) GenericsUtils.createGenericTypeInstance(this);
        setEnabledAt(entity);
        return updateEntity(entity, form);
    }

    private E updateEntity(E entity, F form) {
        setValues(form, entity);
        return repository.save(entity);
    }

    private E deleteEntity(E entity) {
        return disable(entity);
    }

    @SuppressWarnings("unchecked")
    private void setValues(F form, E entity) {
        Class<E> classEntity = (Class<E>) entity.getClass();
        Arrays.stream(classEntity.getDeclaredFields())
                .filter(this::isColumn)
                .forEach(entityField -> {
                    Object formValue = ReflectionUtils.getValue(entityField.getName(), form);
                    if(!Objects.isNull(formValue)) {
                        if(isEntity(entityField)) {
                            formValue = serviceFactory
                                    .getService(getServiceName(entityField))
                                    .findById((Integer) formValue).getResult();
                        }
                        ReflectionUtils.setValue(entityField, entity, formValue);
                    }
                });
    }

    private String getServiceName(Field entityField) {
        return StringCaseFormatUtils
                .upperCamelToLowerCamel(entityField.getType().getSimpleName())
                .concat(ClassSuffixConstants.SERVICE_IMPL);
    }

    private boolean isEntity(Field field) {
        Class<?> superclass = field.getType().getSuperclass();
        return !Objects.isNull(superclass) && superclass.equals(EntityBase.class);
    }

    private boolean isColumn(Field field) {
        return field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(JoinColumn.class);
    }

    @Override
    public E enable(E entity) {
        setEnabledAt(entity);
        return repository.save(entity);
    }

    @Override
    public E disable(E entity) {
        setDisabledAt(entity);
        return repository.save(entity);
    }

    @Override
    public E expire(E entity) {
        setExpiredAt(entity);
        return repository.save(entity);
    }

    @Override
    public E lock(E entity) {
        setLockedAt(entity);
        return repository.save(entity);
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public ServiceResult<Page<E>> findAll(Integer pageNumber, Integer pageSize, String filters, String sortedBy) {
        pageNumber = ObjectUtils.defaultIfNull(pageNumber, paginationProperties.getPageNumber());
        pageSize = ObjectUtils.defaultIfNull(pageSize, paginationProperties.getPageSize());

        final String key = StringCaseFormatUtils.upperCamelToLowerHyphen(getEntityClass().getSimpleName());
        sortedBy = ObjectUtils.defaultIfNull(sortedBy, paginationProperties.getSortingMap().getOrDefault(key, paginationProperties.getSortedBy()));

        return createServiceResultOk(
                repository.findAll(pageRequestBuilder.get(pageSize, pageNumber, sortedBy))
                , messageUtils.getMessage(BASE_SERVICE, MessageUtils.CODE_MESSAGE_OK, MessageUtils.DEFAULT_OFFSET)
        );
    }
}
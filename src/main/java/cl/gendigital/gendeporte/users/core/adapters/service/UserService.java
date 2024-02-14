package cl.gendigital.gendeporte.users.core.adapters.service;

import cl.gendigital.gendeporte.users.core.adapters.service.result.ServiceResult;
import cl.gendigital.gendeporte.users.core.entities.domain.User;

public interface UserService /*extends BaseService<User, UserForm>*/{
    ServiceResult<User> findByUsername(String username);
    ServiceResult<User> findByEmail(String email);
    // ServiceResult<User> saveUserFirebase(UserFireBaseForm userFireBaseForm);
    ServiceResult<User> changePassword(String validationCode, String password);
    ServiceResult<User> requestChangePassword(String email);
    ServiceResult<User> emailValidation(String email, String validationCode);
}


package cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa;

import cl.gendigital.gendeporte.users.infra.adapters.services.response.ServiceResult;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.UserEntityJpa;
import cl.gendigital.gendeporte.users.infra.persistence.repositories.jpa.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class UserJpaAdapter {
    private UserEntityRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserJpaAdapter(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional(readOnly = true)
    public ServiceResult<UserEntityJpa> findByUsername(String username) {
        return createServiceResult(
                userRepository.findByUsername(username),
                null,
                null
        );
    }
}

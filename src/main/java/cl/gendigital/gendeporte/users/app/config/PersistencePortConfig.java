package cl.gendigital.gendeporte.users.app.config;

import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa.UserAdapter;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class PersistencePortConfig {

    @Bean
    public UserPersistencePort userPersistence( UserRepository userRepository) {
        return new UserAdapter( userRepository);
    }
}

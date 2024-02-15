package cl.gendigital.gendeporte.users.app.config;


import cl.gendigital.gendeporte.users.core.adapters.service.UserServiceAdapter;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.core.port.services.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServicePortConfig {
    @Bean
    public UserServicePort userService(
            UserPersistencePort userPersistence) {
        return new UserServiceAdapter(
                userPersistence);
    }
}

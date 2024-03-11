package cl.gendigital.gendeporte.users.app.config;


import cl.gendigital.gendeporte.users.core.adapters.service.UserInfoServiceAdapter;
import cl.gendigital.gendeporte.users.core.adapters.service.UserServiceAdapter;
import cl.gendigital.gendeporte.users.core.port.persistence.UserInfoPersistencePort;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.core.port.services.UserInfoServicePort;
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
    @Bean
    public UserInfoServicePort userInfoService(UserInfoPersistencePort userInfoPersistence,UserPersistencePort userPersistence){
        return new UserInfoServiceAdapter(
                userInfoPersistence,userPersistence);
    }
}

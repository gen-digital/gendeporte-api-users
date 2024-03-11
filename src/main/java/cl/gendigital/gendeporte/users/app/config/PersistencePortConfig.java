package cl.gendigital.gendeporte.users.app.config;

import cl.gendigital.gendeporte.users.core.port.persistence.UserInfoPersistencePort;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa.UserInfoJpaAdapter;
import cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa.UserJpaAdapter;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserInfoRepository;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@RequiredArgsConstructor
public class PersistencePortConfig {

    @Bean
    public UserPersistencePort userPersistence(UserRepository userRepository,UserInfoRepository userInfoRepository) {
        return new UserJpaAdapter(userRepository,userInfoRepository);
    }
    @Bean
    public UserInfoPersistencePort userInfoPersistence(UserInfoRepository userInfoRepository){
        return new UserInfoJpaAdapter(userInfoRepository);
    }

}

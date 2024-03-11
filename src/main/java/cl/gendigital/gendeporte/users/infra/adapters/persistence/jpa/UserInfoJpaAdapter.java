package cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;
import cl.gendigital.gendeporte.users.core.port.persistence.UserInfoPersistencePort;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserInfoRepository;
import cl.gendigital.gendeporte.users.infra.utils.mapper.PersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class UserInfoJpaAdapter implements UserInfoPersistencePort {

    private final UserInfoRepository userInfoRepository;

    @Override
    @Transactional
    public UserInfoPersistence moreInfo(UserInfoPersistence userInfoPersistence, UserInfoPersistence found) {
        var userEnrich = found.merge(userInfoPersistence);
        return userEnrich;
    }

    @Override
    public Optional<UserInfoPersistence> findByUser(User user) {
        return userInfoRepository.findByUser(user);
    }

}

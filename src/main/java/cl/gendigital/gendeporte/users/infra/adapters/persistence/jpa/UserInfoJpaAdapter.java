package cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;
import cl.gendigital.gendeporte.users.core.exceptions.user_info.persistence.UserInfoNotExist;
import cl.gendigital.gendeporte.users.core.port.persistence.UserInfoPersistencePort;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserInfoRepository;
import cl.gendigital.gendeporte.users.infra.utils.mapper.PersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type User info jpa adapter.
 */
@RequiredArgsConstructor
public class UserInfoJpaAdapter implements UserInfoPersistencePort {

    private final UserInfoRepository userInfoRepository;
    @Override
    public Optional<UserInfoPersistence> findByUserId(Integer userId) {
        return userInfoRepository.findByUserId(userId).map(PersistenceMapper::entityToPersistence);
    }

    @Override
    @Transactional
    public UserInfoPersistence moreInfo(UserInfoPersistence userInfoPersistence, Integer id) {
        var userInfo = userInfoRepository.findByUserId(id)
                .orElseThrow(() -> new UserInfoNotExist("GOL"));
        userInfo.setBirthdate(userInfoPersistence.getBirthdate());
        userInfo.setAddress(userInfoPersistence.getAddress());
        userInfo.setRut(userInfoPersistence.getRut());
        userInfo.setNationality(userInfoPersistence.getNationality());
        userInfo.setFirstName(userInfoPersistence.getFirstName());
        userInfo.setMaritalStatus(userInfoPersistence.getMaritalStatus());
        userInfo.setMiddleName(userInfoPersistence.getMiddleName());
        userInfo.setLastName(userInfoPersistence.getLastName());
        userInfo.setPhone(userInfoPersistence.getPhone());
        userInfo.setSecondLastName(userInfoPersistence.getSecondLastName());
        userInfoRepository.save(userInfo);
        return PersistenceMapper.entityToPersistence(userInfo);
    }




}

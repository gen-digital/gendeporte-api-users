package cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.UserNotExist;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.UserInfo;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserInfoRepository;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserRepository;
import cl.gendigital.gendeporte.users.infra.utils.UserUtils;
import cl.gendigital.gendeporte.users.infra.utils.mapper.PersistenceMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort{
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserPersistence> findByUsername(String username) {
        return userRepository.findByUsername(username).map(PersistenceMapper::entityToPersistence);
    }
    @Override
    public Optional<UserPersistence> findByEmail(String email) {
        return userRepository.findByEmail(email).map(PersistenceMapper::entityToPersistence);
    }

    @Override
    public boolean existByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    @Override
    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }


    @Override
    @Transactional
    public Integer save(UserPersistence userPersistence) {
        userPersistence.setValidationCode(UserUtils.validationCode());
        userPersistence.setPassword(passwordEncoder.encode(userPersistence.getPassword()));
        var savedUserEntity =
                userRepository.save(PersistenceMapper.persistenceToEntity(userPersistence));
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(savedUserEntity);
        userInfoRepository.save(userInfo);
        return savedUserEntity.getId();
    }


    @Override
    @Transactional
    public UserPersistence verify(UserPersistence notVerifiedUser,UserPersistence found){
        var userEnabled = found.merge(notVerifiedUser);
        var verify = verify(userEnabled);
        return userEnabled;
    }

    private User verify(UserPersistence userEnabled){
        var found = userRepository
                    .findByUsername(userEnabled.getUsername())
                    .orElseThrow(() -> new UserNotExist(userEnabled.getUsername()));
        found.setEnabledAt(LocalDateTime.now());
        return found;
    }

    @Override
    public Integer id(String username){
        var found = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotExist(username));
        return found.getId();
    }


}

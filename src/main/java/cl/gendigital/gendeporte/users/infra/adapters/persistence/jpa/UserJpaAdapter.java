package cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.core.exceptions.user.persistence.UserNotExist;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserRepository;
import cl.gendigital.gendeporte.users.infra.utils.UserUtils;
import cl.gendigital.gendeporte.users.infra.utils.mapper.PersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {
    private final UserRepository userRepository;

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
        var savedUserEntity =
                userRepository.save(PersistenceMapper.persistenceToEntity(userPersistence));
        return savedUserEntity.getId();
    }

    @Override
    @Transactional
    public UserPersistence enrich(UserPersistence user){
        var userInfo = userRepository
                        .findByUsername(user.getUsername())
                        .orElseThrow(()->new UserNotExist(user.getUsername()));
        userInfo.setPhone(user.getPhone());
        userInfo.setAddress(user.getAddress());
        userInfo.setLastName(user.getLastName());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userInfo);
        return PersistenceMapper.entityToPersistence(userInfo);
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



}

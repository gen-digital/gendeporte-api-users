package cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserRepository;
import cl.gendigital.gendeporte.users.infra.utils.mapper.PersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Integer save(UserPersistence userPersistence) {
        var savedUserEntity =
                userRepository.save(PersistenceMapper.persistenceToEntity(userPersistence));
        return savedUserEntity.getId();
    }



}

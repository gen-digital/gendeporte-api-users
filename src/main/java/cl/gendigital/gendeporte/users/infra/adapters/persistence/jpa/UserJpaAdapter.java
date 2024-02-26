package cl.gendigital.gendeporte.users.infra.adapters.persistence.jpa;

import cl.gendigital.gendeporte.users.core.entities.persistence.UserPersistence;
import cl.gendigital.gendeporte.users.core.port.persistence.UserPersistencePort;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import cl.gendigital.gendeporte.users.infra.persistence.repository.jpa.UserRepository;
import cl.gendigital.gendeporte.users.infra.utils.mapper.PersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


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
        // El banco de caracteres
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String cadena = "";
        for (int x = 0; x < 10; x++) {
            int indiceAleatorio = ThreadLocalRandom.current().nextInt(5,banco.length());
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        userPersistence.setValidationCode(cadena);
        var savedUserEntity =
                userRepository.save(PersistenceMapper.persistenceToEntity(userPersistence));
        return savedUserEntity.getId();
    }


    @Override
    @Transactional
    public UserPersistence verify(UserPersistence user, UserPersistence found){
        var verifyUser = found.merge(user);
        var foundUser = verify(verifyUser);
        return verifyUser;
    }




    private User verify(UserPersistence notVerifiedUser){
        var foundUser =
            userRepository
                    .findByUsername(notVerifiedUser.getUsername())
                    .orElseThrow(()-> null);
        foundUser.setValidationCode(notVerifiedUser.getValidationCode());
        foundUser.setEnabledAt(notVerifiedUser.getEnabledAt());
        userRepository.save(foundUser);
        return foundUser;
    }



}

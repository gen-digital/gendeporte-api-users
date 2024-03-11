package cl.gendigital.gendeporte.users.infra.persistence.repository.jpa;


import cl.gendigital.gendeporte.users.core.entities.persistence.UserInfoPersistence;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{

    Optional<UserInfo> findById(Integer integer);
    Optional<UserInfoPersistence> findByUser(User user);
}

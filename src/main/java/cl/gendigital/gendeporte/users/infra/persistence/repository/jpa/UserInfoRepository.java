package cl.gendigital.gendeporte.users.infra.persistence.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import cl.gendigital.gendeporte.users.infra.persistence.model.jpa.UserInfo;



@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{

    Optional<UserInfo> findById(Integer integer);
    Optional<UserInfo> findByUserId(Integer userId);

}

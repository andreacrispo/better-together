package my.app.bt.bettertogether.repository;

import my.app.bt.bettertogether.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    @Query("select u from users u where u.username = :username")
    Optional<UserEntity> findByUsername(@Param("username") String username);
}

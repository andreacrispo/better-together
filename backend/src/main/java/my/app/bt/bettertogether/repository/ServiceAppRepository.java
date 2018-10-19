package my.app.bt.bettertogether.repository;

import my.app.bt.bettertogether.entity.ServiceApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceAppRepository extends JpaRepository<ServiceApp, Long> {

    @Query("select s from ServiceApp s where s.user.username = :username")
    List<ServiceApp> findAllByUsername(@Param("username") String username);

    @Query("select s from ServiceApp s where s.id = :serviceId and s.user.username = :username")
    Optional<ServiceApp> findByIdAndUsername(@Param("serviceId") Long serviceId, @Param("username") String username);
}

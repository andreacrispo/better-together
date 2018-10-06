package my.app.bt.bettertogether.repository;

import my.app.bt.bettertogether.entity.ServiceApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAppRepository extends JpaRepository<ServiceApp, Long> {
}

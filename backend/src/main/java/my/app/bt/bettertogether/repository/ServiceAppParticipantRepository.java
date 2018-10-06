package my.app.bt.bettertogether.repository;

import my.app.bt.bettertogether.entity.ServiceAppParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAppParticipantRepository extends JpaRepository<ServiceAppParticipant, Long> {

}

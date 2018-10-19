package my.app.bt.bettertogether.repository;

import my.app.bt.bettertogether.entity.ServiceAppParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceAppParticipantRepository extends JpaRepository<ServiceAppParticipant, Long> {


    @Query("select sp " +
            " from ServiceAppParticipant sp " +
            " where sp.service.id = :serviceId " +
            "    and sp.participant.id = :participantId " +
            "    and sp.paymentDate = :paymentDate  ")
    ServiceAppParticipant findByServiceAndParticipantAndPaymentDate(
            @Param("serviceId") Long serviceId,
            @Param("participantId") Long participantId,
            @Param("paymentDate") LocalDate paymentDate);


    @Query("select sp " +
            " from ServiceAppParticipant sp " +
            " where sp.service.id = :serviceId " +
            "    and sp.paymentDate = :paymentDate  " +
            " order by sp.participant.name ")
    List<ServiceAppParticipant> findParticipantsByPaymentDate(@Param("serviceId") Long serviceId,
                                                              @Param("paymentDate") LocalDate paymentDate);

}

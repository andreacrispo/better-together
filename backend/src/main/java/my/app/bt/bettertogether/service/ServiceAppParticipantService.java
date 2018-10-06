package my.app.bt.bettertogether.service;

import my.app.bt.bettertogether.Utils;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.entity.Participant;
import my.app.bt.bettertogether.entity.ServiceApp;
import my.app.bt.bettertogether.entity.ServiceAppParticipant;
import my.app.bt.bettertogether.repository.ServiceAppParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAppParticipantService {


    @Autowired
    private ServiceAppParticipantRepository repository;

    public void addParticipantToService(ServiceApp service, Participant participantEntity, ParticipantDto participantDto) {
        ServiceAppParticipant serviceParticipant = new ServiceAppParticipant();
        serviceParticipant.setService(service);
        serviceParticipant.setParticipant(participantEntity);
        serviceParticipant.setHasPaid(true);
        serviceParticipant.setPricePaid(participantDto.getPricePaid());
        serviceParticipant.setPaymentDate(Utils.obtainPaymentDate(participantDto));

        repository.save(serviceParticipant);
    }
}

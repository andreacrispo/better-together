package my.app.bt.bettertogether.service;

import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.Utils;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.entity.Participant;
import my.app.bt.bettertogether.entity.ServiceApp;
import my.app.bt.bettertogether.entity.ServiceAppParticipant;
import my.app.bt.bettertogether.exception.ResourceNotFoundException;
import my.app.bt.bettertogether.repository.ParticipantRepository;
import my.app.bt.bettertogether.repository.ServiceAppParticipantRepository;
import my.app.bt.bettertogether.repository.ServiceAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ServiceAppParticipantService {


    @Autowired
    private ServiceAppParticipantRepository repository;
    @Autowired
    private ServiceAppRepository serviceAppRepo;
    @Autowired
    private ParticipantRepository participantRepo;

    @Autowired
    private ParticipantService participantService;

    @Transactional
    public ServiceApp addParticipantToService(Long serviceId, ParticipantDto participantDto) {
        log.info("Add participant to service id {}", serviceId);
        // TODO: REFACTOR !
        ServiceApp service = this.serviceAppRepo.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));

        Participant participantEntity = this.participantService.getOrCreate(participantDto);

        ServiceAppParticipant relationship = new ServiceAppParticipant();
        relationship.setService(service);
        relationship.setParticipant(participantEntity);
        relationship.setHasPaid(participantDto.getHasPaid() != null ? participantDto.getHasPaid() : false);
        relationship.setPricePaid(participantDto.getPricePaid() != null ? participantDto.getPricePaid() : null);
        relationship.setPaymentDate(Utils.obtainPaymentDate(participantDto));
        service.getServiceAppParticipants().add(relationship);

        participantRepo.save(participantEntity);
        return serviceAppRepo.save(service);
    }


    @Transactional
    public ServiceAppParticipant editParticipantFromService(Long serviceId, ParticipantDto participantDto) {
        log.info("Edit participant from service id {}", serviceId);
        // TODO: REFACTOR !
        log.info("Remove participant from service id {}", serviceId);
        LocalDate paymentDate = LocalDate.of(participantDto.getYearPaid(), participantDto.getMonthPaid(), 1);
        ServiceAppParticipant relationship = repository.findByServiceAndParticipantAndPaymentDate(serviceId, participantDto.getId(), paymentDate);

        Participant participantEntity = this.participantService.getOrCreate(participantDto);
        participantEntity.setName(participantDto.getName());

        relationship.setParticipant(participantEntity);
        relationship.setPaymentDate(paymentDate);
        relationship.setHasPaid(participantDto.getHasPaid() != null ? participantDto.getHasPaid() : false);
        relationship.setPricePaid(participantDto.getPricePaid());


        participantRepo.save(participantEntity);
        repository.saveAndFlush(relationship);
        return relationship;
    }


    @Transactional
    public ServiceApp removeParticipantFromService(Long serviceId, Long participantId, ParticipantDto participantDto) {
        log.info("Remove participant from service id {}", serviceId);
        LocalDate paymentDate = LocalDate.of(participantDto.getYearPaid(), participantDto.getMonthPaid(), 1);
        ServiceAppParticipant relationship = repository.findByServiceAndParticipantAndPaymentDate(serviceId, participantId, paymentDate);

        ServiceApp service = this.serviceAppRepo.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));

        service.getServiceAppParticipants().remove(relationship);

        return serviceAppRepo.save(service);

    }


    public List<ServiceAppParticipant> findParticipantsByPaymentDate(Long serviceId, LocalDate paymentDate) {
        return this.repository.findParticipantsByPaymentDate(serviceId, paymentDate);
    }


}

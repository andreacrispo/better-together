package my.app.bt.bettertogether.service;

import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.Utils;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.domain.ServiceParticipantDto;
import my.app.bt.bettertogether.entity.Participant;
import my.app.bt.bettertogether.entity.ServiceApp;
import my.app.bt.bettertogether.entity.ServiceAppParticipant;
import my.app.bt.bettertogether.repository.ServiceAppRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceAppService {

    @Autowired
    private ServiceAppRepository serviceAppRepository;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ServiceAppParticipantService serviceAppParticipantService;

    public List<ServiceParticipantDto> findAll() {
        return this.serviceAppRepository.findAll()
        .stream()
        .map(s ->
             ServiceParticipantDto.builder()
                    .serviceId(s.getId())
                    .name(s.getName())
                    .description(s.getDescription())
                    .monthlyPrice(s.getMonthlyPrice())
                    .participants(Collections.emptyList())
             .build()
        ).collect(Collectors.toList());
    }

    public ServiceParticipantDto findById(Long id, LocalDate paymentDate) {
        log.info("Find Service with id: {}", id);
        ServiceApp s = this.serviceAppRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "ServiceApp"));

        // TODO: Dont filter date here, filter in sql query
        List<ParticipantDto> participantList = s.getServiceAppParticipants()
                .stream()
                .filter(sp-> Utils.paymentDateMatches(sp.getPaymentDate(), paymentDate))
                .map(sp ->
                    ParticipantDto.builder()
                        .id(sp.getParticipant().getId())
                        .email(sp.getParticipant().getEmail())
                        .name(sp.getParticipant().getName())
                        .hasPaid(sp.getHasPaid())
                        .pricePaid(sp.getPricePaid())
                    .build()
                ).collect(Collectors.toList());

        return ServiceParticipantDto.builder()
                .serviceId(s.getId())
                .name(s.getName())
                .participants(participantList)
                .build();
    }


    public void save(ServiceApp serviceApp) {
        log.info("Create Service with name: {}", serviceApp.getName());
        this.serviceAppRepository.save(serviceApp);
    }



    public void addParticipant(Long serviceId, ParticipantDto participant) {
        ServiceApp service = this.serviceAppRepository.findById(serviceId).orElseThrow(() -> new ObjectNotFoundException(serviceId, "ServiceApp"));
        Participant participantEntity = this.participantService.getOrCreate(participant);


        this.serviceAppParticipantService.addParticipantToService(service, participantEntity, participant);
    }


    public void delete(Long id) {
        log.info("Delete Service with id: {}", id);
        this.serviceAppRepository.deleteById(id);
    }
}

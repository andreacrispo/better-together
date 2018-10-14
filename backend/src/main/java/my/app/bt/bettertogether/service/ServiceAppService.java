package my.app.bt.bettertogether.service;

import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.Utils;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.domain.ServiceParticipantDto;
import my.app.bt.bettertogether.entity.ServiceApp;
import my.app.bt.bettertogether.exception.ResourceNotFoundException;
import my.app.bt.bettertogether.repository.ServiceAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
                                .participantNumber(s.getParticipantNumber())
                                .participants(Collections.emptyList())
                                .build()
                ).collect(Collectors.toList());
    }

    public ServiceParticipantDto findById(Long id, LocalDate paymentDate) {
        log.info("Find Service with id: {}", id);
        ServiceApp s = this.serviceAppRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", id));

        List<ParticipantDto> participantList = this.serviceAppParticipantService.findParticipantsByPaymentDate(id, paymentDate)
                .stream()
                .map(sp ->
                        ParticipantDto.builder()
                                .id(sp.getParticipant().getId())
                                .email(sp.getParticipant().getEmail())
                                .name(sp.getParticipant().getName())
                                .hasPaid(sp.getHasPaid())
                                .pricePaid(sp.getPricePaid())
                                .monthPaid(sp.getPaymentDate().getMonthValue())
                                .yearPaid(sp.getPaymentDate().getYear())
                                .build())
                .collect(Collectors.toList());

        return ServiceParticipantDto.builder()
                .serviceId(s.getId())
                .name(s.getName())
                .monthlyPrice(s.getMonthlyPrice())
                .description(s.getDescription())
                .icon(s.getIcon())
                .participantNumber(s.getParticipantNumber())
                .participants(participantList)
                .build();
    }

    @Transactional
    public ServiceApp save(ServiceParticipantDto serviceApp) {
        log.info("Create Service with name: {}", serviceApp.getName());
        ServiceApp serviceEntiy = new ServiceApp();
        serviceEntiy.setName(serviceApp.getName());
        serviceEntiy.setDescription(serviceApp.getDescription());
        serviceEntiy.setMonthlyPrice(serviceApp.getMonthlyPrice());
        serviceEntiy.setParticipantNumber(serviceApp.getParticipantNumber());
        return serviceAppRepository.save(serviceEntiy);
    }


    @Transactional
    public ServiceApp edit(Long id, ServiceParticipantDto serviceApp) {
        log.info("Edit service with id: {}", id);
        ServiceApp serviceEntiy = serviceAppRepository.getOne(id);
        serviceEntiy.setName(serviceApp.getName());
        serviceEntiy.setDescription(serviceApp.getDescription());
        serviceEntiy.setMonthlyPrice(serviceApp.getMonthlyPrice());
        serviceEntiy.setParticipantNumber(serviceApp.getParticipantNumber());
        return serviceAppRepository.save(serviceEntiy);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Delete Service with id: {}", id);
        this.serviceAppRepository.deleteById(id);

    }


    public void copyParticipantsFromPreviousMonth(Long serviceId, int currentMonth, int year) {
        LocalDate previousDate = Utils.createPreviosuDate(currentMonth, year);

        this.serviceAppParticipantService.findParticipantsByPaymentDate(serviceId, previousDate)
                .stream()
                .map(sp ->
                        ParticipantDto.builder()
                                .id(sp.getParticipant().getId())
                                .email(sp.getParticipant().getEmail())
                                .name(sp.getParticipant().getName())
                                .hasPaid(false)
                                .pricePaid(null)
                                .monthPaid(currentMonth)
                                .yearPaid(year)
                                .build())
                .map(participantDto ->
                        this.serviceAppParticipantService.addParticipantToService(serviceId, participantDto))
                .collect(Collectors.toList());
    }

}

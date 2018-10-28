package my.app.bt.bettertogether.service;

import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.util.DateUtils;
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

    private ServiceAppRepository serviceAppRepository;
    private ServiceAppParticipantService serviceAppParticipantService;
    private UserService userService;

    @Autowired
    public ServiceAppService(ServiceAppRepository serviceAppRepository,
                             ServiceAppParticipantService serviceAppParticipantService, UserService userService) {
        this.serviceAppRepository = serviceAppRepository;
        this.serviceAppParticipantService = serviceAppParticipantService;
        this.userService = userService;
    }

    public List<ServiceParticipantDto> findAll(String username) {
        return this.serviceAppRepository.findAllByUsername(username)
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

    public ServiceParticipantDto findById(Long id, LocalDate paymentDate, String username) {
        log.info("Find Service with id: {}", id);
        ServiceApp s = this.serviceAppRepository.findByIdAndUsername(id, username)
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
    public ServiceApp save(ServiceParticipantDto serviceApp, String username) {
        log.info("Create Service with name: {}", serviceApp.getName());
        ServiceApp serviceEntity = new ServiceApp();
        serviceEntity.setName(serviceApp.getName());
        serviceEntity.setDescription(serviceApp.getDescription());
        serviceEntity.setMonthlyPrice(serviceApp.getMonthlyPrice());
        serviceEntity.setParticipantNumber(serviceApp.getParticipantNumber());
        serviceEntity.setUser(userService.findByUsername(username));
        return serviceAppRepository.save(serviceEntity);
    }


    @Transactional
    public ServiceApp edit(Long id, ServiceParticipantDto serviceApp, String username) {
        log.info("Edit service with id: {}", id);
        ServiceApp serviceEntity = this.serviceAppRepository.findByIdAndUsername(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Service", "id", id));

        serviceEntity.setName(serviceApp.getName());
        serviceEntity.setDescription(serviceApp.getDescription());
        serviceEntity.setMonthlyPrice(serviceApp.getMonthlyPrice());
        serviceEntity.setParticipantNumber(serviceApp.getParticipantNumber());
        return serviceAppRepository.save(serviceEntity);
    }

    @Transactional
    public void delete(Long id, String username) {
        log.info("Delete Service with id: {}", id);
        ServiceApp serviceEntity = this.serviceAppRepository.findByIdAndUsername(id, username)
                                 .orElseThrow(() -> new ResourceNotFoundException("Service", "id", id));

        this.serviceAppRepository.delete(serviceEntity);

    }


    public void copyParticipantsFromPreviousMonth(Long serviceId, int currentMonth, int year, String username) {
        LocalDate previousDate = DateUtils.createPreviousDate(currentMonth, year);

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
                        this.serviceAppParticipantService.addParticipantToService(serviceId, participantDto, username))
                .collect(Collectors.toList());
    }


}

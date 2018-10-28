package my.app.bt.bettertogether.controller;


import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.domain.ServiceParticipantDto;
import my.app.bt.bettertogether.entity.ServiceApp;
import my.app.bt.bettertogether.entity.ServiceAppParticipant;
import my.app.bt.bettertogether.service.ServiceAppParticipantService;
import my.app.bt.bettertogether.service.ServiceAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/services")
public class ServiceAppController {

    private ServiceAppParticipantService serviceParticipantService;
    private ServiceAppService serviceAppService;

    @Autowired
    public ServiceAppController(ServiceAppService serviceAppService, ServiceAppParticipantService serviceParticipantService) {
        this.serviceAppService = serviceAppService;
        this.serviceParticipantService = serviceParticipantService;
    }

    @GetMapping(value = {"", "/"})
    public Flux<ServiceParticipantDto> findAllByUser(@AuthenticationPrincipal Principal principal) {
        log.info("Principal {}", principal.getName());
        return Flux.fromIterable(this.serviceAppService.findAll(principal.getName()));
    }


    @GetMapping("/{id}")
    public Mono<ServiceParticipantDto> findById(
            @AuthenticationPrincipal Principal principal,
            @PathVariable("id") Long id,
            @RequestParam(value = "year", required = false) Integer yearPayment,
            @RequestParam(value = "month", required = false) Integer monthPayment) {

        LocalDate paymentDate = monthPayment == null || yearPayment == null
                ? LocalDate.now()
                : LocalDate.of(yearPayment, monthPayment, 1);

        return Mono.just(this.serviceAppService.findById(id, paymentDate, principal.getName()));
    }


    @PostMapping(value = {"", "/"})
    public Mono<ServiceApp> save(
            @AuthenticationPrincipal Principal principal,
            @RequestBody ServiceParticipantDto serviceApp) {
        return Mono.just(this.serviceAppService.save(serviceApp, principal.getName()));
    }

    @PutMapping("/{id}")
    public Mono<ServiceApp> edit(
            @AuthenticationPrincipal Principal principal,
            @PathVariable("id") Long id,
            @RequestBody ServiceParticipantDto serviceApp) {
        return Mono.just(this.serviceAppService.edit(id, serviceApp, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(
            @AuthenticationPrincipal Principal principal,
            @PathVariable("id") Long id) {
        this.serviceAppService.delete(id, principal.getName());
        return Mono.empty();
    }


    @PostMapping("{serviceId}/participants")
    public Mono<ServiceApp> addParticipant(
                    @AuthenticationPrincipal Principal principal,
                    @PathVariable("serviceId") Long serviceId,
                    @RequestBody ParticipantDto participant) {
        return Mono.just(this.serviceParticipantService.addParticipantToService(serviceId, participant, principal.getName()));
    }

    @PostMapping("{serviceId}/participants/{participantId}/update")
    public Mono<ServiceAppParticipant> editParticipant(
                    @AuthenticationPrincipal Principal principal,
                     @PathVariable("serviceId") Long serviceId,
                     @PathVariable("participantId") Long participantId,
                     @RequestBody ParticipantDto participant) {
        return Mono.just(this.serviceParticipantService.editParticipantFromService(serviceId, participant, principal.getName()));
    }


    @PostMapping("{serviceId}/participants/{participantId}/delete")
    public Mono<Void> removeParticipant( @AuthenticationPrincipal Principal principal,
                                        @PathVariable("serviceId") Long serviceId,
                                        @PathVariable("participantId") Long participantId,
                                        @RequestBody ParticipantDto participant) {
        this.serviceParticipantService.removeParticipantFromService(serviceId, participantId, participant, principal.getName());
        return Mono.empty();
    }


    @PostMapping("{serviceId}/participants/copy")
    public Mono<Void> copyParticipants( @AuthenticationPrincipal Principal principal,
            @PathVariable("serviceId") Long id,
            @RequestParam(value = "year", required = false) Integer currentYear,
            @RequestParam(value = "month", required = false) Integer currentMonth) {

        this.serviceAppService.copyParticipantsFromPreviousMonth(id, currentMonth, currentYear, principal.getName());
        return Mono.empty();
    }

}



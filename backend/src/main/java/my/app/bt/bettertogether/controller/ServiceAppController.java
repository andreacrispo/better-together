package my.app.bt.bettertogether.controller;


import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.domain.ServiceParticipantDto;
import my.app.bt.bettertogether.entity.ServiceApp;
import my.app.bt.bettertogether.entity.ServiceAppParticipant;
import my.app.bt.bettertogether.service.ServiceAppParticipantService;
import my.app.bt.bettertogether.service.ServiceAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = {"http://localhost:4200", "*"})
public class ServiceAppController {

    @Autowired
    private ServiceAppService serviceAppService;
    @Autowired
    private ServiceAppParticipantService serviceParticipantService;

    @GetMapping(value = {"", "/"})
    public Flux<ServiceParticipantDto> findAll() {
        return Flux.fromIterable(this.serviceAppService.findAll());
    }


    @GetMapping("/{id}")
    public Mono<ServiceParticipantDto> findById(
            @PathVariable("id") Long id,
            @RequestParam(value = "year", required = false) Integer yearPayment,
            @RequestParam(value = "month", required = false) Integer monthPayment) {

        LocalDate paymentDate = monthPayment == null || yearPayment == null
                ? LocalDate.now()
                : LocalDate.of(yearPayment, monthPayment, 1);

        return Mono.just(this.serviceAppService.findById(id, paymentDate));
    }


    @PostMapping(value = {"", "/"})
    public Mono<ServiceApp> save(@RequestBody ServiceParticipantDto serviceApp) {
        return Mono.just(this.serviceAppService.save(serviceApp));
    }

    @PutMapping("/{id}")
    public Mono<ServiceApp> edit(@PathVariable("id") Long id, @RequestBody ServiceParticipantDto serviceApp) {
        return Mono.just(this.serviceAppService.edit(id, serviceApp));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        this.serviceAppService.delete(id);
        return Mono.empty();
    }


    @PostMapping("{serviceId}/participants")
    public Mono<ServiceApp> addParticipant(@PathVariable("serviceId") Long serviceId,
                                           @RequestBody ParticipantDto participant) {
        return Mono.just(this.serviceParticipantService.addParticipantToService(serviceId, participant));
    }

    @PostMapping("{serviceId}/participants/{participantId}/update")
    public Mono<ServiceAppParticipant> editParticipant(@PathVariable("serviceId") Long serviceId,
                                                       @PathVariable("participantId") Long participantId,
                                                       @RequestBody ParticipantDto participant) {
        return Mono.just(this.serviceParticipantService.editParticipantFromService(serviceId, participant));
    }


    @PostMapping("{serviceId}/participants/{participantId}/delete")
    public Mono<Void> removeParticipant(@PathVariable("serviceId") Long serviceId,
                                        @PathVariable("participantId") Long participantId,
                                        @RequestBody ParticipantDto participant) {
        this.serviceParticipantService.removeParticipantFromService(serviceId, participantId, participant);
        return Mono.empty();
    }


    @PostMapping("{serviceId}/participants/copy")
    public Mono<Void> copyParticipants(
            @PathVariable("serviceId") Long id,
            @RequestParam(value = "year", required = false) Integer currentYear,
            @RequestParam(value = "month", required = false) Integer currentMonth) {

        this.serviceAppService.copyParticipantsFromPreviousMonth(id, currentMonth, currentYear);
        return Mono.empty();
    }

}



package my.app.bt.bettertogether.controller;


import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.domain.ServiceParticipantDto;
import my.app.bt.bettertogether.entity.Participant;
import my.app.bt.bettertogether.entity.ServiceApp;
import my.app.bt.bettertogether.service.ServiceAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/services")
public class ServiceAppController {

    @Autowired
    private ServiceAppService serviceAppService;

    @GetMapping(value = {"", "/"})
    @CrossOrigin(origins = "http://localhost:4200")
    public Flux<ServiceParticipantDto> findAll() {
       return  Flux.fromIterable( this.serviceAppService.findAll());
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Mono<ServiceParticipantDto> findById(
            @PathVariable("id") Long id,
            @RequestParam(value = "year",  required = false) Integer yearPayment,
            @RequestParam(value = "month", required = false) Integer monthPayment) {

        LocalDate paymentDate = monthPayment == null || yearPayment == null
                ? LocalDate.now()
                : LocalDate.of(yearPayment,monthPayment,1);

        return  Mono.just(this.serviceAppService.findById(id, paymentDate));
    }


    @PostMapping(value = {"", "/"})
    public Mono<Void> save(@RequestBody ServiceApp serviceApp){
        this.serviceAppService.save(serviceApp);
        return Mono.empty();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Long id) {
        this.serviceAppService.delete(id);
        return Mono.empty();
    }

    @PostMapping("{serviceId}/participants")
    public Mono<Void> save(@PathVariable("serviceId") Long serviceId,
                           @RequestBody ParticipantDto participant){
        this.serviceAppService.addParticipant(serviceId, participant);
        return Mono.empty();
    }

}



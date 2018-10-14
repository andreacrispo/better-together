package my.app.bt.bettertogether.service;

import lombok.extern.slf4j.Slf4j;
import my.app.bt.bettertogether.domain.ParticipantDto;
import my.app.bt.bettertogether.entity.Participant;
import my.app.bt.bettertogether.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository participantRepository;

    public Participant getOrCreate(ParticipantDto participantDto) {
        return participantRepository.findById(participantDto.getId() != null ? participantDto.getId() : -1)
                .orElse(
                    Participant.builder()
                        .name(participantDto.getName())
                        .email(participantDto.getEmail())
                    .build()
                );
    }
}

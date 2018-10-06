package my.app.bt.bettertogether.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.app.bt.bettertogether.entity.Participant;
import my.app.bt.bettertogether.entity.ServiceApp;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ServiceParticipantDto {

    private Long serviceId;

    private String name;

    private String description;

    private String icon;

    private Double monthlyPrice;


    private List<ParticipantDto> participants;
}

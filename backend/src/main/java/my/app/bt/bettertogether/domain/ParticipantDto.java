package my.app.bt.bettertogether.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ParticipantDto {

    private Long id;

    private String name;

    private String email;

    private Boolean hasPaid;

    private Double pricePaid;

    private Integer yearPaid;
    private Integer monthPaid;
}

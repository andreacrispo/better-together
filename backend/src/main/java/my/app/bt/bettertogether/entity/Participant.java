package my.app.bt.bettertogether.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(exclude = {"serviceAppParticipants"})
@Entity(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long id;

    private String name;

    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "participant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ServiceAppParticipant> serviceAppParticipants;
}

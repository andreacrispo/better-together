package my.app.bt.bettertogether.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"serviceAppParticipants"})
@Entity
@Table(name = "service")
public class ServiceApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long id;

    private String name;

    private String description;

    private String icon;

    private Double monthlyPrice;

    private Integer participantNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ServiceAppParticipant> serviceAppParticipants;
}

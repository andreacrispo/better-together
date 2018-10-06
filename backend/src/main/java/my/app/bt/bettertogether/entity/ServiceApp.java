package my.app.bt.bettertogether.entity;

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


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "service", cascade = CascadeType.ALL )
    private Set<ServiceAppParticipant> serviceAppParticipants;
}

package cargotracker.routing.domain.model.aggregates;


import cargotracker.routing.domain.model.valueobjects.Schedule;
import cargotracker.routing.domain.model.valueobjects.VoyageNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "voyage")
@NamedQueries({
        @NamedQuery(name = "Voyage.findByVoyageNumber", query = "Select v from Voyage v where v.voyageNumber = :voyageNumber"),
        @NamedQuery(name = "Voyage.findAll", query = "Select v from Voyage v order by v.voyageNumber.voyageNumber")})
@Getter
@Setter
@NoArgsConstructor
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @NotNull
    private Schedule schedule;

    @Embedded
    private VoyageNumber voyageNumber;

    public Voyage(VoyageNumber voyageNumber, Schedule schedule) {
        this.schedule = schedule;
        this.voyageNumber = voyageNumber;
    }

    public Voyage(VoyageNumber voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

}

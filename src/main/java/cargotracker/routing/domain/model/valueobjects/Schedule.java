package cargotracker.routing.domain.model.valueobjects;

import cargotracker.routing.domain.model.entities.CarrierMovement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * A Voyage schedule
 */
@Embeddable
@NoArgsConstructor
public class Schedule {

    public static final Schedule EMPTY = new Schedule();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "voyage")
    private List<CarrierMovement> carrierMovements;

    Schedule(List<CarrierMovement> carrierMovements) {
        this.carrierMovements = carrierMovements;
    }

    public List<CarrierMovement> getCarrierMovements() {
        return Collections.unmodifiableList(carrierMovements);
    }
}

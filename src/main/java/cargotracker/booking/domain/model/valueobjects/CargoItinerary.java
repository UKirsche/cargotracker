package cargotracker.booking.domain.model.valueobjects;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
public class CargoItinerary {

    public static final CargoItinerary EMPTY_ITINERARY = new CargoItinerary();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cargo_id")
    private List<Leg> legs = Collections.emptyList();

    public CargoItinerary(List<Leg> legs) {
        this.legs = legs;
    }
}

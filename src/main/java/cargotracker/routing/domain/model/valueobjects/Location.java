package cargotracker.routing.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Location class represented by a unique 5-diigit UN Location code.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Location {

    @Column(name = "arrival_location_id")
    private String unLocCode;

    public Location(String unLocCode){this.unLocCode = unLocCode;}

}

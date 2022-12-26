package cargotracker.booking.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Location class represented by a unique 5-diigit UN Location code.
 */
@Embeddable
@Getter
@Setter
public class Location {

    //Identifier for Entity
    @Column(name = "origin_id")
    private String unLocCode;
    public Location(){}
    public Location(String unLocCode){this.unLocCode = unLocCode;}

}

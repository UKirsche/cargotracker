package cargotracker.routing.domain.model.entities;


import cargotracker.booking.domain.model.entities.Location;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "carrier_movement")
@Getter
@NoArgsConstructor
public class CarrierMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name="arrival_date")
    private Date arrivalDate;
    @Temporal(TemporalType.DATE)
    @Column(name="departure_Date")
    private Date departureDate;
    @Embedded
    private Location arrivalLocation;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "departure_location_id"))
    private Location departureLocation;

    public CarrierMovement(Location departureLocation,
                           Location arrivalLocation, Date departureDate, Date arrivalDate) {

        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }

}

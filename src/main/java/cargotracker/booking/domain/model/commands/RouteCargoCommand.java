package cargotracker.booking.domain.model.commands;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Command Class to assign a route to a booked cargo
 */
@Slf4j
@Getter
@Setter
public class RouteCargoCommand {
    private String cargoBookingId;

    public RouteCargoCommand(String cargoBookingId){
        this.setCargoBookingId(cargoBookingId);
    }

}

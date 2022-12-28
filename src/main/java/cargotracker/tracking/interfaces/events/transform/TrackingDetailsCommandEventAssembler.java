package cargotracker.tracking.interfaces.events.transform;


import cargotracker.shareddomain.events.CargoRoutedEvent;
import cargotracker.tracking.domain.model.commands.AssignTrackingNumberCommand;
import lombok.extern.slf4j.Slf4j;

/**
 * Assembler class to convert the Cargo Routed Event to the Assign Tracking Number Command Model
 */
@Slf4j
public class TrackingDetailsCommandEventAssembler {

    /**
     * Static method within the Assembler class
     * @param cargoRoutedEvent
     * @return AssignTrackingNumberCommand Model
     */
    public static AssignTrackingNumberCommand toCommandFromEvent(CargoRoutedEvent cargoRoutedEvent){
        log.info("CargoRoutedEvent BookingId {}", cargoRoutedEvent.getContent().getBookingId());
        return new AssignTrackingNumberCommand(cargoRoutedEvent.getContent().getBookingId(), "");
    }
}

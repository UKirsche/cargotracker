package cargotracker.booking.interfaces.events.transform;

import cargotracker.booking.domain.model.commands.HandlingCargoCommand;
import cargotracker.shareddomain.events.CargoHandledEvent;
import cargotracker.shareddomain.events.CargoHandledEventData;

public class CargoHandlingEventAssembler {

    public static HandlingCargoCommand toCommandFromEvent(CargoHandledEvent cargoHandledEvent) {
        CargoHandledEventData content = cargoHandledEvent.getContent();
        return new HandlingCargoCommand(content.getBookingId(), content.getHandlingId(), content.getHandlingType(), content.getVoyageNumber(), content.getHandlingLocation());
    }
}

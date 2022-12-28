package cargotracker.booking.interfaces.events;

import cargotracker.booking.application.internal.commandservices.CargoBookingCommandService;
import cargotracker.booking.interfaces.events.transform.CargoHandlingEventAssembler;
import cargotracker.shareddomain.events.CargoHandledEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CargoHandledEventHandler {

    @Inject
    private CargoBookingCommandService commandService; // Application Service Dependency


    /**
     * Cargo Handled Event handler
     *
     * @param event
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void observeCargoHandledEvent(@Observes CargoHandledEvent event) {
        log.info("Cargo Handled Event in Booking Bounded Context: {}", event.getContent());
        commandService.updateCargoForHandling(CargoHandlingEventAssembler.toCommandFromEvent(event));
    }


}

package cargotracker.tracking.interfaces.events;

import cargotracker.shareddomain.events.CargoRoutedEvent;
import cargotracker.tracking.application.internal.commandservices.AssignTrackingIdCommandService;
import cargotracker.tracking.interfaces.events.transform.TrackingDetailsCommandEventAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CargoRoutedEventHandler {

    @Inject
    private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency

    /**
     * Cargo Routed Event Handler Method
     *
     * @param event EventHandler hört auf CargoRouted
     */

    @Transactional
    public void observeCargoRoutedEvent(@Observes CargoRoutedEvent event) {
        LOG.info("Observes CargoRoutedEvent in Tracking Domain for BookingId {}", event.getContent().getBookingId());
        assignTrackingIdCommandService.assignTrackingNumberToCargo(TrackingDetailsCommandEventAssembler.toCommandFromEvent(event));
    }
}

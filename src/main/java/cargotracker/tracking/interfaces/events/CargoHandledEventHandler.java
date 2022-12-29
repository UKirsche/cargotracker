package cargotracker.tracking.interfaces.events;

import cargotracker.shareddomain.events.CargoHandledEvent;
import cargotracker.shareddomain.events.CargoHandledEventData;
import cargotracker.tracking.application.internal.commandservices.AssignTrackingIdCommandService;
import cargotracker.tracking.interfaces.events.transform.TrackingActivityCommandEventAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CargoHandledEventHandler {

    @Inject
    private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency


    /**
     * Cargo Handled Event handler
     *
     * @param event
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void observeCargoHandledEvent(@Observes CargoHandledEvent event) {
        CargoHandledEventData eventData = event.getContent();
        LOG.info("Cargo Handled Event {} with {}, {}, {}, {},{}",
                eventData,
                eventData.getBookingId(),
                eventData.getHandlingLocation(),
                eventData.getHandlingCompletionTime().toString(),
                eventData.getHandlingType(),
                eventData.getVoyageNumber());
        assignTrackingIdCommandService.addTrackingEvent(TrackingActivityCommandEventAssembler.toCommandFromEvent(event));
    }


}

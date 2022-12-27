package cargotracker.booking.interfaces.events;

import cargotracker.booking.application.internal.commandservices.CargoBookingCommandService;
import cargotracker.shareddomain.events.CargoHandledEvent;
import cargotracker.shareddomain.events.CargoHandledEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
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
        System.out.println("***Cargo Handled Event****" + event.getContent());
        CargoHandledEventData eventData = event.getContent();
        System.out.println(eventData.getBookingId());
        System.out.println(eventData.getHandlingLocation());
        System.out.println(eventData.getHandlingCompletionTime());
        System.out.println(eventData.getHandlingType());
        System.out.println(eventData.getVoyageNumber());
//            assignTrackingIdCommandService.addTrackingEvent(TrackingActivityCommandEventAssembler.toCommandFromEvent(event));
    }


}

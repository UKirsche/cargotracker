package cargotracker.tracking.interfaces.events;

import cargotracker.shareddomain.events.CargoHandledEvent;
import cargotracker.shareddomain.events.CargoHandledEventData;
import cargotracker.tracking.application.internal.commandservices.AssignTrackingIdCommandService;
import cargotracker.tracking.interfaces.events.transform.TrackingActivityCommandEventAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CargoHandledEventHandler {

        @Inject
        private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency


        /**
         * Cargo Handled Event handler
         * @param event
         */
        @Transactional
        public void observeCargoHandledEvent(@Observes CargoHandledEvent event) {
            System.out.println("***Cargo Handled Event****"+event.getContent());
                CargoHandledEventData eventData = event.getContent();
                System.out.println(eventData.getBookingId());
                System.out.println(eventData.getHandlingLocation());
                System.out.println(eventData.getHandlingCompletionTime());
                System.out.println(eventData.getHandlingType());
                System.out.println(eventData.getVoyageNumber());
            assignTrackingIdCommandService.addTrackingEvent(
                    TrackingActivityCommandEventAssembler.toCommandFromEvent(event));
        }


}

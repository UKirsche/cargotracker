package cargotracker.tracking.interfaces.events;

import cargotracker.shareddomain.events.CargoBookedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class CargoBookedEventHandler {

    public void testEventObserving(@Observes CargoBookedEvent event) {
        // Processing of an event
        System.out.println("***Just a Test***"+event.getId());
    }
}

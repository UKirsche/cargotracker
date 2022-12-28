package cargotracker.tracking.interfaces.events;

import cargotracker.shareddomain.events.CargoBookedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CargoBookedEventHandler {

    public void testEventObserving(@Observes CargoBookedEvent event) {
        // Processing of an event
        log.info("Event Handler auf Tracking für CargoBooked Event {}", event.getId());
    }
}

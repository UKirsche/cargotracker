package cargotracker.booking.application.internal.commandservices;

import cargotracker.booking.application.internal.outboundservices.acl.ExternalCargoRoutingService;
import cargotracker.booking.domain.model.aggregates.BookingId;
import cargotracker.booking.domain.model.aggregates.Cargo;
import cargotracker.booking.domain.model.commands.BookCargoCommand;
import cargotracker.booking.domain.model.commands.RouteCargoCommand;
import cargotracker.booking.domain.model.valueobjects.CargoItinerary;
import cargotracker.booking.infrastructure.repositories.jpa.CargoRepository;
import cargotracker.shareddomain.events.CargoBookedEvent;
import cargotracker.shareddomain.events.CargoRoutedEvent;
import cargotracker.shareddomain.events.CargoRoutedEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


/**
 * Application Service class for the Cargo Booking Command
 */
@ApplicationScoped
public class CargoBookingCommandService {

    @Inject
    private CargoRepository cargoRepository; // Outbound Service to connect to the Booking Bounded Context MySQL Database Instance

    @Inject
    private Event<CargoBookedEvent> cargoBookedEventControl;

    @Inject
    private Event<CargoRoutedEvent> cargoRoutedEventControl; // Event that needs to be raised when the Cargo is Booked

    @Inject
    private ExternalCargoRoutingService externalCargoRoutingService;

    /**
     * Service Command method to book a new Cargo
     * @return BookingId of the Cargo
     */
    @Transactional // Inititate the Transaction
    public BookingId bookCargo(BookCargoCommand bookCargoCommand){
        String bookingId = cargoRepository.nextBookingId();
        bookCargoCommand.setBookingId(bookingId);
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.store(cargo); //Store the Cargo

        CargoBookedEvent cargoBookedEvent = new CargoBookedEvent();
        cargoBookedEvent.setId(bookingId); //Set the content of the event
        cargoBookedEventControl.fire(cargoBookedEvent);


        return new BookingId(bookingId);
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */
    @Transactional
    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand) {
        Cargo cargo = cargoRepository.find(new BookingId(routeCargoCommand.getCargoBookingId()));

        CargoItinerary cargoItinerary = externalCargoRoutingService.fetchRouteForSpecification(cargo.getRouteSpecification());

        cargo.assignToRoute(cargoItinerary);
        cargoRepository.store(cargo);

        CargoRoutedEvent cargoRoutedEvent = getCargoRoutedEvent(routeCargoCommand);
        cargoRoutedEventControl.fire(cargoRoutedEvent);
    }

    private CargoRoutedEvent getCargoRoutedEvent(RouteCargoCommand routeCargoCommand) {
        CargoRoutedEvent cargoRoutedEvent = new CargoRoutedEvent();
        CargoRoutedEventData eventData = new CargoRoutedEventData();
        eventData.setBookingId(routeCargoCommand.getCargoBookingId());
        cargoRoutedEvent.setContent(eventData);
        return cargoRoutedEvent;
    }
}

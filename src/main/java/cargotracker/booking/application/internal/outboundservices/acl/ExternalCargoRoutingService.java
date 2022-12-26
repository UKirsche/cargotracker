package cargotracker.booking.application.internal.outboundservices.acl;

import cargotracker.booking.domain.model.entities.Location;
import cargotracker.booking.domain.model.valueobjects.CargoItinerary;
import cargotracker.booking.domain.model.valueobjects.Leg;
import cargotracker.booking.domain.model.valueobjects.RouteSpecification;
import cargotracker.booking.domain.model.valueobjects.Voyage;
import cargotracker.booking.infrastructure.services.http.ExternalCargoRoutingClient;
import cargotracker.shareddomain.model.TransitEdge;
import cargotracker.shareddomain.model.TransitPath;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Anti Corruption Service Class
 */
@ApplicationScoped
public class ExternalCargoRoutingService {

    @Inject
    private ExternalCargoRoutingClient externalCargoRoutingClient;

    /**
     * The Booking Bounded Context makes an external call to the Routing Service of the Routing Bounded Context to
     * fetch the Optimal Itinerary for a Cargo based on the Route Specification
     * @param routeSpecification
     * @return
     */
    public CargoItinerary fetchRouteForSpecification(RouteSpecification routeSpecification) {

        TransitPath transitPath = externalCargoRoutingClient.findOptimalRoute(
                routeSpecification.getOrigin().getUnLocCode(),
                routeSpecification.getDestination().getUnLocCode(),
                routeSpecification.getArrivalDeadline().toString()
        );

        List<Leg> legs = mapTo(transitPath);

        return new CargoItinerary(legs);

    }

    /**
     * ACL:Maps the Transaitpath to Legs for the relevant Domain -> albeit its a shared Domain.
     *
     * @param transitPath Transipath
     * @return Legs
     */
    private List<Leg> mapTo(TransitPath transitPath) {
        List<Leg> legs = transitPath.getTransitEdges()
                .stream()
                .map(this::toLeg)
                .collect(Collectors.toCollection(() -> new ArrayList<>(transitPath.getTransitEdges().size())));
        return legs;
    }

    /**
     * Anti-corruption layer conversion method from the routing service's domain model (TransitEdges)
     * to the domain model recognized by the Booking Bounded Context (Legs)
     * @param edge
     * @return
     */
    private Leg toLeg(TransitEdge edge) {
        return new Leg(
                new Voyage(edge.getVoyageNumber()),
                new Location(edge.getFromUnLocode()),
                new Location(edge.getToUnLocode()),
                edge.getFromDate(),
                edge.getToDate());
        }
}

package cargotracker.booking.infrastructure.services.http;

import cargotracker.shareddomain.model.TransitPath;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * Type safe Rest client for the Routing Service API
 */

@RequestScoped
public class ExternalCargoRoutingClient {

    @Inject
    @RestClient
    CargoRoutingClient routingClient;

    public TransitPath findOptimalRoute(String origin, String destination, String arrivalDeadline) {
        return routingClient.findOptimalRoute(origin, destination, arrivalDeadline);
    }

}

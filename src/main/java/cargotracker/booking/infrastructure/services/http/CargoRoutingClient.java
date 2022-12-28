package cargotracker.booking.infrastructure.services.http;

import api.rest.UnknownUriExceptionMapper;
import cargotracker.shareddomain.model.TransitPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "routingclient", baseUri = "http://localhost:9080/cargotracker")
@RegisterProvider(UnknownUriExceptionMapper.class)
@Path("/serviceapi/voyageRouting")
public interface CargoRoutingClient {

    @GET
    @Path("/optimalRoute")
    @Produces({"application/json"})
    TransitPath findOptimalRoute(@QueryParam("origin") String originUnLocode, @QueryParam("destination") String destinationUnLocode, @QueryParam("deadline") String deadline);
}
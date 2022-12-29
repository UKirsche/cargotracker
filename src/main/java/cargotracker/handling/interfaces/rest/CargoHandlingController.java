package cargotracker.handling.interfaces.rest;

import cargotracker.handling.application.internal.commandservices.HandlingActivityRegistrationCommandService;
import cargotracker.handling.interfaces.rest.dto.HandlingActivityRegistrationResource;
import cargotracker.handling.interfaces.rest.transform.HandlingActivityRegistrationCommandDTOAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for the REST API
 */
@Path("/cargohandling")
@ApplicationScoped
@Slf4j
public  class CargoHandlingController {

    @Inject
    private HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService; // Application Service Dependency


    /**
     * POST method to register Handling Activities
     * @param handlingActivityRegistrationResource
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerHandlingActivity(HandlingActivityRegistrationResource handlingActivityRegistrationResource){
        LOG.info("Register handling activity for {} , {} ", handlingActivityRegistrationResource.getBookingId(), handlingActivityRegistrationResource.getHandlingType());
        handlingActivityRegistrationCommandService
                .registerHandlingActivityService(HandlingActivityRegistrationCommandDTOAssembler.toCommandFromDTO(handlingActivityRegistrationResource));
        final Response returnValue = Response.ok()
                .entity("Handling Activity Registered")
                .build();
        return returnValue;
    }
}

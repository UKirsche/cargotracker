package cargotracker.booking.interfaces.rest;

import cargotracker.booking.application.internal.commandservices.CargoBookingCommandService;
import cargotracker.booking.domain.model.aggregates.BookingId;
import cargotracker.booking.interfaces.rest.dto.BookCargoResource;
import cargotracker.booking.interfaces.rest.transform.BookCargoCommandDTOAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/cargobooking")
@ApplicationScoped
@Slf4j
public class CargoBookingController {


    @Inject
    private CargoBookingCommandService cargoBookingCommandService; // Application Service Dependency

    /**
     * POST method to book a cargo
     * @param bookCargoResource
     */

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response bookCargo(BookCargoResource bookCargoResource){
        log.info("****Book Cargo{}", cargoBookingCommandService);
        BookingId bookingId = cargoBookingCommandService
                .bookCargo(BookCargoCommandDTOAssembler.toCommandFromDTO(bookCargoResource));

        final Response returnValue = Response.ok()
                .entity(bookingId)
                .build();
        return returnValue;
    }


}

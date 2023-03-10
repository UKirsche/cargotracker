package cargotracker.tracking.application.internal.commandservices;

import cargotracker.tracking.domain.model.aggregates.TrackingActivity;
import cargotracker.tracking.domain.model.aggregates.TrackingNumber;
import cargotracker.tracking.domain.model.commands.AddTrackingEventCommand;
import cargotracker.tracking.domain.model.commands.AssignTrackingNumberCommand;
import cargotracker.tracking.domain.model.entities.TrackingBookingId;
import cargotracker.tracking.infrastructure.repositories.jpa.TrackingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


/**
 * Application Service class for the Tracking Command Service
 */
@ApplicationScoped
@Slf4j
public class AssignTrackingIdCommandService {

    @Inject
    private TrackingRepository trackingRepository;

    /**
     * Service Command method to assign a tracking id to the booked cargo
     * @return Tracking Number of the Cargo
     */
    @Transactional // Inititate the Transaction
    public TrackingNumber assignTrackingNumberToCargo(AssignTrackingNumberCommand assignTrackingNumberCommand) {
        String trackingNumber = getTrackingNumber(assignTrackingNumberCommand);
        TrackingActivity activity = new TrackingActivity(assignTrackingNumberCommand);
        LOG.info("Tracking Bounded Context: Going to store in repository {}", trackingNumber);
        trackingRepository.store(activity); //Store the Cargo

        return new TrackingNumber(trackingNumber);
    }

    private String getTrackingNumber(AssignTrackingNumberCommand assignTrackingNumberCommand) {
        String trackingNumber = trackingRepository.nextTrackingNumber();
        assignTrackingNumberCommand.setTrackingNumber(trackingNumber);
        return trackingNumber;
    }

    /**
     * Service Command method to assign a route to a Cargo
     *
     * @param addTrackingEventCommand
     */
    @Transactional
    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand) {
        TrackingActivity trackingActivity = trackingRepository.findByBookingId(
                new TrackingBookingId(addTrackingEventCommand.getBookingId()));

        trackingActivity.addTrackingEvent(addTrackingEventCommand);
        trackingRepository.store(trackingActivity);
    }


}

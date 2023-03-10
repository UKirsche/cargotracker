package cargotracker.tracking.domain.model.aggregates;

import cargotracker.tracking.domain.model.commands.AddTrackingEventCommand;
import cargotracker.tracking.domain.model.commands.AssignTrackingNumberCommand;
import cargotracker.tracking.domain.model.entities.TrackingActivityEvent;
import cargotracker.tracking.domain.model.entities.TrackingBookingId;
import cargotracker.tracking.domain.model.valueobjects.TrackingEvent;
import cargotracker.tracking.domain.model.valueobjects.TrackingEventType;
import cargotracker.tracking.domain.model.valueobjects.TrackingLocation;
import cargotracker.tracking.domain.model.valueobjects.TrackingVoyageNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NamedQueries({
        @NamedQuery(name = "TrackingActivity.findAll",
                query = "Select t from TrackingActivity t"),
        @NamedQuery(name = "TrackingActivity.findByTrackingNumber",
                query = "Select t from TrackingActivity t where t.trackingNumber.trackingNumber = :trackingNumber"),
        @NamedQuery(name = "TrackingActivity.getAllTrackingNos",
                query = "Select t.trackingNumber.trackingNumber from TrackingActivity t"),
        @NamedQuery(name = "TrackingActivity.findByBookingNumber",
                query = "Select t from TrackingActivity t where t.bookingId.bookingId = :bookingId")})
@Table(name = "tracking_activity")
@NoArgsConstructor
@Getter
public class TrackingActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TrackingNumber trackingNumber; // Aggregate Identifier
    @Embedded
    private TrackingBookingId bookingId;
    @Embedded
    private TrackingActivityEvent trackingActivityEvent;

    /**
     * Creates a new Tracking Number
     * @param assignTrackingNumberCommand
     */
    public TrackingActivity(AssignTrackingNumberCommand assignTrackingNumberCommand){
        this.trackingNumber = new TrackingNumber(assignTrackingNumberCommand.getTrackingNumber());
        this.bookingId = new TrackingBookingId((assignTrackingNumberCommand.getBookingId()));
        this.trackingActivityEvent = TrackingActivityEvent.EMPTY_ACTIVITY;
        /*this.trackingActivityEvent.getTrackingEvents().add(
                new TrackingEvent(
                new TrackingVoyageNumber(""),
                new TrackingLocation(""),
                new TrackingEventType("ROUTED",new Date())));*/

    }

    /**
     * Add a tracking event to the Tracking Details
     * @param addTrackingEventCommand
     */
    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand){
        TrackingEvent trackingEvent = new TrackingEvent(
                new TrackingVoyageNumber(addTrackingEventCommand.getVoyageNumber()),
                new TrackingLocation(addTrackingEventCommand.getLocation()),
                new TrackingEventType(addTrackingEventCommand.getEventType(),addTrackingEventCommand.getEventTime()));
        this.trackingActivityEvent.getTrackingEvents().add(trackingEvent);
    }

}

package cargotracker.tracking.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Tracking Event Details
 */
@Entity
@Table(name = "tracking_handling_events")
@Getter
@NoArgsConstructor
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private TrackingVoyageNumber trackingVoyageNumber;
    @Embedded
    private TrackingLocation trackingLocation;
    @Embedded
    private TrackingEventType trackingEventType;

    public TrackingEvent(
            TrackingVoyageNumber trackingVoyageNumber,
            TrackingLocation trackingLocation,
            TrackingEventType trackingEventType){
        this.trackingEventType = trackingEventType;
        this.trackingVoyageNumber = trackingVoyageNumber;
        this.trackingLocation = trackingLocation;
    }

}

package cargotracker.booking.domain.model.valueobjects;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class LastCargoHandledEvent {

    @Column(name="last_handling_event_id")
    private Integer handlingEventId;
    @Column(name = "last_handling_event_type")
    private String handlingEventType;
    @Column(name = "last_handling_event_voyage")
    private String handlingEventVoyage;
    @Column(name = "last_handling_event_location")
    private String handlingEventLocation;
    // Null object pattern.
    public static final LastCargoHandledEvent EMPTY = new LastCargoHandledEvent();

    /**
     * @param handlingEventId       id from Handling the Cargo
     * @param handlingEventType     Type
     * @param handlingEventVoyage   VoyageNumber
     * @param handlingEventLocation Location of Handling
     */
    public LastCargoHandledEvent(Integer handlingEventId, String handlingEventType, String handlingEventVoyage, String handlingEventLocation) {
        this.handlingEventId = handlingEventId;
        this.handlingEventType = handlingEventType;
        this.handlingEventVoyage = handlingEventVoyage;
        this.handlingEventLocation = handlingEventLocation;
    }
}

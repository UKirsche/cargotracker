package cargotracker.shareddomain.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Event Data for the Cargo Handled Event
 */
@Getter
@Setter
@NoArgsConstructor
public class CargoHandledEventData {

    private String handlingId;

    private String bookingId;
    private String handlingType;
    private Date handlingCompletionTime;
    private String handlingLocation;
    private String voyageNumber;

    public CargoHandledEventData(String handlingId, String bookingId, String handlingType, Date handlingCompletionTime, String handlingLocation, String voyageNumber) {
        this.handlingId = handlingId;
        this.bookingId = bookingId;
        this.handlingType = handlingType;
        this.handlingCompletionTime = handlingCompletionTime;
        this.handlingLocation = handlingLocation;
        this.voyageNumber = voyageNumber;
    }
}

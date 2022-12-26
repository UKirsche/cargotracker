package cargotracker.tracking.domain.model.commands;

import lombok.Getter;
import lombok.Setter;

/**
 * Assign Tracking Number Command class
 */
@Getter
@Setter
public class AssignTrackingNumberCommand {
    private String bookingId;
    private String trackingNumber;

    public AssignTrackingNumberCommand(String bookingId,String trackingNumber){
        this.bookingId = bookingId;
        this.trackingNumber = trackingNumber;
    }

}

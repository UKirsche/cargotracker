package cargotracker.handling.domain.model.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Command to Register an Handling Activity
 */
@Getter
@Setter
public class HandlingActivityRegistrationCommand {

    private Date completionTime;
    private String bookingId;
    private String voyageNumber;
    private String unLocode;
    private String handlingType;

    public HandlingActivityRegistrationCommand(String bookingId, String voyageNumber, String unLocode, String handlingType, Date completionTime){
        this.setCompletionTime(completionTime);
        this.setBookingId(bookingId);
        this.setVoyageNumber(voyageNumber);
        this.setUnLocode(unLocode);
        this.setHandlingType(handlingType);
    }
}

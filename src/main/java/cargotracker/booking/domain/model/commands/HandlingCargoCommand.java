package cargotracker.booking.domain.model.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HandlingCargoCommand {

    private String bookingId;
    private String handlingId;
    private String handlingType;
    private String handlingVoyage;
    private String handlingLocation;
}

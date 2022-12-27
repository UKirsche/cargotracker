package cargotracker.booking.interfaces.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Resource class for the Book Cargo Command API.
 * Contains Routespecification and amount.
 */
@Getter
@Setter
@NoArgsConstructor
public class BookCargoResource {

    private int bookingAmount;
    private String originLocation;
    private String destLocation;
    private LocalDate destArrivalDeadline;

    public BookCargoResource(int bookingAmount,
                             String originLocation, String destLocation, LocalDate destArrivalDeadline){

        this.bookingAmount = bookingAmount;
        this.originLocation = originLocation;
        this.destLocation = destLocation;
        this.destArrivalDeadline = destArrivalDeadline;
    }
}

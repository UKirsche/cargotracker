package cargotracker.booking.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

/**
 * Aggregate Identifier for the Cargo Aggregate
 */
@Embeddable
@Getter
public class BookingId implements Serializable {

    @Column(name="booking_id")
    private String bookingId;

    public BookingId(){}

    public BookingId(String bookingId){this.bookingId = bookingId;}
}

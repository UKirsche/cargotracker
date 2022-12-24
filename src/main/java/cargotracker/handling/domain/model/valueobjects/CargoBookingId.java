package cargotracker.handling.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CargoBookingId {
    @Column(name = "booking_id")
    private String bookingId;
    public CargoBookingId(){}
    public CargoBookingId(String bookingId){this.bookingId = bookingId;}
    public void setBookingId(String bookingId){this.bookingId = bookingId;}
    public String getBookingId(){return this.bookingId;}
}

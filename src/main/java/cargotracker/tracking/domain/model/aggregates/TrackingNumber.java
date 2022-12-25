package cargotracker.tracking.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class TrackingNumber {

    @Column(name="tracking_number")
    private String trackingNumber;

    public TrackingNumber(String trackingNumber){this.trackingNumber = trackingNumber;}

}

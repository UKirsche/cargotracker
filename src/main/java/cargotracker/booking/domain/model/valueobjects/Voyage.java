package cargotracker.booking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Class representing the Cargo Voyage
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Voyage {

    @Column(name = "voyage_number")
    private String voyageNumber;

    public Voyage(String voyageId){
        this.voyageNumber = voyageNumber;
    }

}

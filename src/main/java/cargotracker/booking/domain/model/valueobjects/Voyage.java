package cargotracker.booking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *  Class representing the Cargo Voyage
 */
@Embeddable
public class Voyage {

    @Column(name = "voyage_number")
    private String voyageNumber;

    public Voyage(){}

    public Voyage(String voyageId){
        this.voyageNumber = voyageNumber;
    }

    public String getVoyageId(){return this.voyageNumber;}

    public void setVoyageId(String voyageId){this.voyageNumber = voyageNumber;}
}

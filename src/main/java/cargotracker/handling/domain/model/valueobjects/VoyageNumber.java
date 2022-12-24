package cargotracker.handling.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VoyageNumber {
    @Column(name="voyage_number")
    private String voyageNumber;
    public VoyageNumber(){}
    public VoyageNumber(String voyageNumber){this.voyageNumber = voyageNumber;}
    public String getVoyageNumber(){return this.voyageNumber;}
    public void setVoyageNumber(String voyageNumber){this.voyageNumber = voyageNumber;}
}

package cargotracker.shareddomain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents an edge in a path through a graph, describing the route of a
 * cargo.
 */
@Getter
@Setter
@NoArgsConstructor
public class TransitEdge implements Serializable {

    private String voyageNumber;
    private String fromUnLocode;
    private String toUnLocode;
    private Date fromDate;
    private Date toDate;


    public TransitEdge(String voyageNumber, String fromUnLocode,
            String toUnLocode, Date fromDate, Date toDate) {
        this.voyageNumber = voyageNumber;
        this.fromUnLocode = fromUnLocode;
        this.toUnLocode = toUnLocode;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public String toString() {
        return "TransitEdge{" + "voyageNumber=" + voyageNumber
                + ", fromUnLocode=" + fromUnLocode + ", toUnLocode="
                + toUnLocode + ", fromDate=" + fromDate
                + ", toDate=" + toDate + '}';
    }
}
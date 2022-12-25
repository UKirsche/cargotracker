package cargotracker.shareddomain.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Getter
@Setter
public class TransitPath implements Serializable {

    private List<TransitEdge> transitEdges;

    public TransitPath() {
        this.transitEdges = new ArrayList<>();
    }

    public TransitPath(List<TransitEdge> transitEdges) {
        this.transitEdges = transitEdges;
    }

    @Override
    public String toString() {
        return "TransitPath{" + "transitEdges=" + transitEdges + '}';
    }
}

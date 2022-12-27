package cargotracker.shareddomain.events;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoHandledEvent {

    private CargoHandledEventData content;
}

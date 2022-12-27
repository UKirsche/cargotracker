package cargotracker.handling.application.internal.commandservices;

import cargotracker.handling.domain.model.aggregates.HandlingActivity;
import cargotracker.handling.domain.model.commands.HandlingActivityRegistrationCommand;
import cargotracker.handling.domain.model.valueobjects.CargoBookingId;
import cargotracker.handling.domain.model.valueobjects.Location;
import cargotracker.handling.domain.model.valueobjects.Type;
import cargotracker.handling.domain.model.valueobjects.VoyageNumber;
import cargotracker.handling.infrastructure.repositories.jpa.HandlingActivityRepository;
import cargotracker.shareddomain.events.CargoHandledEvent;
import cargotracker.shareddomain.events.CargoHandledEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
public class HandlingActivityRegistrationCommandService {

        @Inject
        private HandlingActivityRepository handlingActivityRepository;

        @Inject
        private Event<CargoHandledEvent> cargoHandledEventControl; // Event that needs to be raised when the Cargo is Handled


        /**
         * Service Command method to register a new Handling Activity
         *
         * @return BookingId of the CargoBookingId
         */
        @Transactional
        public void registerHandlingActivityService(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand) {
                System.out.println("Handling Voyage Number is" + handlingActivityRegistrationCommand.getVoyageNumber());
                HandlingActivity handlingActivity = getHandlingActivity(handlingActivityRegistrationCommand);

                HandlingActivity handlingActivityStored = handlingActivityRepository.store(handlingActivity);
                CargoHandledEvent cargoHandledEvent = createCargoHandledEvent(handlingActivityRegistrationCommand, handlingActivityStored);

                System.out.println("*****cargohandlede" + handlingActivityRegistrationCommand.getBookingId() + " " + handlingActivityRegistrationCommand.getHandlingType()
                        + " " + handlingActivityRegistrationCommand.getCompletionTime() + " " + handlingActivityRegistrationCommand.getUnLocode());

                cargoHandledEventControl.fire(cargoHandledEvent);

        }

        private HandlingActivity getHandlingActivity(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand) {
                HandlingActivity handlingActivity;
                String voyageNumber = handlingActivityRegistrationCommand.getVoyageNumber();
                if (StringUtils.isEmpty(voyageNumber)) {
                        handlingActivity = getHandlingActivityNoVoyage(handlingActivityRegistrationCommand);
                } else {
                        handlingActivity = getHandlingActivityWithVoyage(handlingActivityRegistrationCommand);
                }
                return handlingActivity;
        }

        private HandlingActivity getHandlingActivityNoVoyage(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand) {
                HandlingActivity handlingActivity;
                handlingActivity = new HandlingActivity(
                        new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                        handlingActivityRegistrationCommand.getCompletionTime(),
                        Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                        new Location(handlingActivityRegistrationCommand.getUnLocode()));
                return handlingActivity;
        }

        private HandlingActivity getHandlingActivityWithVoyage(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand) {
                HandlingActivity handlingActivity = new HandlingActivity(
                        new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                        handlingActivityRegistrationCommand.getCompletionTime(),
                        Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                        new Location(handlingActivityRegistrationCommand.getUnLocode()),
                        new VoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber()));
                return handlingActivity;
        }

        /**
         * Creates the HandledEvent
         *
         * @param handlingActivityRegistrationCommand Command with relevant fields
         * @param handlingActivity
         * @return Event
         */
        private CargoHandledEvent createCargoHandledEvent(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand, HandlingActivity handlingActivity) {
                CargoHandledEvent cargoHandledEvent = new CargoHandledEvent();
                CargoHandledEventData eventData = new CargoHandledEventData();
                eventData.setHandlingId(handlingActivity.getId().toString());
                eventData.setBookingId(handlingActivityRegistrationCommand.getBookingId());
                eventData.setHandlingCompletionTime(handlingActivityRegistrationCommand.getCompletionTime());
                eventData.setHandlingLocation(handlingActivityRegistrationCommand.getUnLocode());
                eventData.setHandlingType(handlingActivityRegistrationCommand.getHandlingType());
                eventData.setVoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber());

                System.out.println("***Event Data ***" + eventData);
                cargoHandledEvent.setContent(eventData);
                return cargoHandledEvent;
        }
}

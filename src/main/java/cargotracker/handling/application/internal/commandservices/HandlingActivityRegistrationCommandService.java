package cargotracker.handling.application.internal.commandservices;

import cargotracker.booking.domain.model.entities.Location;
import cargotracker.handling.domain.model.aggregates.HandlingActivity;
import cargotracker.handling.domain.model.commands.HandlingActivityRegistrationCommand;
import cargotracker.handling.domain.model.valueobjects.CargoBookingId;
import cargotracker.handling.domain.model.valueobjects.Type;
import cargotracker.handling.domain.model.valueobjects.VoyageNumber;
import cargotracker.handling.infrastructure.repositories.jpa.HandlingActivityRepository;
import cargotracker.shareddomain.events.CargoHandledEvent;
import cargotracker.shareddomain.events.CargoHandledEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HandlingActivityRegistrationCommandService {

        @Inject
        private HandlingActivityRepository handlingActivityRepository;

        @Inject
        private Event<CargoHandledEvent> cargoHandledEventControl; // Event that needs to be raised when the Cargo is Handled



        /**
         * Service Command method to register a new Handling Activity
         * @return BookingId of the CargoBookingId
         */
        @Transactional
        public void registerHandlingActivityService(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand){
                System.out.println("Handling Voyage Number is"+handlingActivityRegistrationCommand.getVoyageNumber());
                if(!handlingActivityRegistrationCommand.getVoyageNumber().equals("")) {
                        HandlingActivity handlingActivity = new HandlingActivity(
                                new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                                handlingActivityRegistrationCommand.getCompletionTime(),
                                Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                                new Location(handlingActivityRegistrationCommand.getUnLocode()),
                                new VoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber()));
                        handlingActivityRepository.store(handlingActivity);


                }else{
                        HandlingActivity handlingActivity = new HandlingActivity(
                                new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                                handlingActivityRegistrationCommand.getCompletionTime(),
                                Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                                new Location(handlingActivityRegistrationCommand.getUnLocode()));
                        handlingActivityRepository.store(handlingActivity);
                }


                CargoHandledEvent cargoHandledEvent = new CargoHandledEvent();
                CargoHandledEventData eventData = new CargoHandledEventData();
                eventData.setBookingId(handlingActivityRegistrationCommand.getBookingId());
                eventData.setHandlingCompletionTime(handlingActivityRegistrationCommand.getCompletionTime());
                eventData.setHandlingLocation(handlingActivityRegistrationCommand.getUnLocode());
                eventData.setHandlingType(handlingActivityRegistrationCommand.getHandlingType());
                eventData.setVoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber());

                System.out.println("***Event Data ***"+eventData);
                cargoHandledEvent.setContent(eventData);

                System.out.println("*****cargohandlede"+handlingActivityRegistrationCommand.getBookingId()+ " " + handlingActivityRegistrationCommand.getHandlingType()
                + " " + handlingActivityRegistrationCommand.getCompletionTime() + " " +handlingActivityRegistrationCommand.getUnLocode() );

                cargoHandledEventControl.fire(cargoHandledEvent);

        }
}

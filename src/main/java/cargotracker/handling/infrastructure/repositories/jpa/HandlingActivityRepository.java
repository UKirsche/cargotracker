package cargotracker.handling.infrastructure.repositories.jpa;


import cargotracker.handling.domain.model.aggregates.HandlingActivity;
import cargotracker.handling.domain.model.valueobjects.HandlingActivityHistory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class HandlingActivityRepository {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    public HandlingActivity store(HandlingActivity handlingActivity) {
        HandlingActivity stored = entityManager.merge(handlingActivity);
        entityManager.flush();
        return stored;
    }

    /**
     * Get the entire handling history for a Cargo
     *
     * @param bookingId
     * @return
     */
    public HandlingActivityHistory lookupHandlingHistoryOfCargo(String bookingId) {
        return new HandlingActivityHistory(entityManager.createNamedQuery(
                        "HandlingEvent.findByBookingId", HandlingActivity.class)
                .setParameter("bookingId", bookingId).getResultList());
    }
}

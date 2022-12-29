package cargotracker.booking.infrastructure.repositories.jpa;

import cargotracker.booking.domain.model.aggregates.BookingId;
import cargotracker.booking.domain.model.aggregates.Cargo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Repository class for the Cargo Aggregate. Deals with all repository operations
 * related to the state of the Cargo
 */
@ApplicationScoped
@Slf4j
public class CargoRepository {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "cargotracker")
    private EntityManager entityManager;

    /**
     * Returns the Cargo Aggregate based on the Booking Identifier of a Cargo
     * @param bookingId
     * @return
     */
    public Cargo find(BookingId bookingId) {
        Cargo cargo;
        try {
            cargo = entityManager.createNamedQuery("Cargo.findByBookingId", Cargo.class)
                    .setParameter("bookingId", bookingId)
                    .getSingleResult();
        } catch (NoResultException e) {
            LOG.error("Find called on non-existant Booking ID:", e);
            cargo = null;
        }

        return cargo;
    }

    /**
     * Stores the Cargo Aggregate
     * @param cargo
     */
    public void store(Cargo cargo) {
        LOG.debug("Entity Manager is {}", entityManager);
        entityManager.persist(cargo);
        entityManager.flush();
    }

    /**
     * Gets next Booking Identifier
     * @return
     */

    public String nextBookingId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return random.substring(0, random.indexOf("-"));
    }

    /**
     * Find all Cargo Aggregates
     * @return
     */
    public List<Cargo> findAll() {
        return entityManager.createNamedQuery("Cargo.findAll", Cargo.class)
                .getResultList();
    }

    /**
     * Get all Booking Identifiers
     * @return
     */
    public List<BookingId> findAllBookingIds() {
        List<BookingId> bookingIds = new ArrayList<BookingId>();

        try {
            bookingIds = entityManager.createNamedQuery(
                    "Cargo.getAllTrackingIds", BookingId.class).getResultList();
        } catch (NoResultException e) {
            LOG.error("Unable to get all tracking IDs", e);
        }

        return bookingIds;
    }
}

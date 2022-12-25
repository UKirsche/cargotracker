package cargotracker.routing.infrastructure.repositories.jpa;



import cargotracker.routing.domain.model.aggregates.Voyage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Repository class for the Voyage Aggregate.
 */
@ApplicationScoped
public class VoyageRepository {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(
            VoyageRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Stores the Voyage Aggregate
     * @param voyage
     */
    public void store(Voyage voyage) {
        entityManager.persist(voyage);
        entityManager.flush();
    }

    /**
     * Find all Voyage Aggregates
     * @return
     */
    public List<Voyage> findAll() {
        List<Voyage> resultList = new ArrayList<>();
        try {
            resultList = entityManager.createNamedQuery("Voyage.findAll", Voyage.class).getResultList();

        } catch (Exception ex) {
            System.err.println("Fehler beim Abrufen der Voyages" + ex.getMessage());
        }
        return resultList;
    }


}

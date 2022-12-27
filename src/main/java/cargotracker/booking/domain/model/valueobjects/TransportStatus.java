package cargotracker.booking.domain.model.valueobjects;


/**
 * These statuses contain domain knowledge for cargo en route. More information here in Eric Evans DDD, p. 34
 */
public enum TransportStatus {
    NOT_RECEIVED,
    IN_PORT,
    ONBOARD_CARRIER,
    CLAIMED,
    UNKNOWN;
}

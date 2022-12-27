package cargotracker.booking.domain.model.valueobjects;


/**
 * These statuses contain domain knowledge for cargo en route or in ports. More information here in Eric Evans DDD, pp. 25
 */
public enum TransportStatus {
    NOT_RECEIVED,
    IN_PORT,
    ONBOARD_CARRIER,
    CLAIMED,
    UNKNOWN;
}

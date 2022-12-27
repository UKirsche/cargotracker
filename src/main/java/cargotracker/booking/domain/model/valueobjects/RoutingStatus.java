package cargotracker.booking.domain.model.valueobjects;

/**
 * Contains domain knowledge about cargo routing, also very important for the Delivery progress.
 */
public enum RoutingStatus {

    NOT_ROUTED,
    ROUTED,
    MISROUTED;
}

package cargotracker.tracking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;


@Embeddable
public class TrackingEventType {

    @Column(name = "event_type")
    private String eventType;
    @Column(name="event_time")
    @Temporal(TemporalType.DATE)
    private Date eventTime;

    public TrackingEventType(){}
    public TrackingEventType(String eventType,Date eventTime){this.eventType = eventType;this.eventTime=eventTime;}
    public void setEventType(String eventType){this.eventType = eventType;}
    public String getEventType(){return this.eventType;}
    public void setEventTime(Date eventTime){this.eventTime = eventTime;}
    public Date getEventTime(){return this.eventTime; }
}

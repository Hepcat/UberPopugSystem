package org.uberpopug.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MetaData {

    @JsonProperty(value = "event_id")
    private String eventId;

    @JsonProperty(value = "event_version")
    private EventVersion eventVersion;

    @JsonProperty(value = "event_name")
    private EventName eventName;

    @JsonProperty(value = "event_time")
    private Date eventTime;

    @JsonProperty(value = "producer")
    private String producer;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public EventVersion getEventVersion() {
        return eventVersion;
    }

    public void setEventVersion(EventVersion eventVersion) {
        this.eventVersion = eventVersion;
    }

    public EventName getEventName() {
        return eventName;
    }

    public void setEventName(EventName eventName) {
        this.eventName = eventName;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}

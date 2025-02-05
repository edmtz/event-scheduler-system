package com.edmtz.dto.response;

import com.edmtz.model.Event;

public class EventResponseDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final String dateTime;
    private final String location;
    private final String createdByUsername;

    public EventResponseDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.dateTime = String.valueOf(event.getDateTime());
        this.location = event.getLocation();
        this.createdByUsername = event.getCreatedBy().getUsername();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }
}

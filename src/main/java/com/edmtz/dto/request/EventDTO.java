package com.edmtz.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class EventDTO {

    @NotBlank(message = "Event name is required")
    private String name;
    @NotBlank(message = "Event description is required")
    private String description;
    @NotBlank(message = "Event datetime is required")
    private LocalDateTime dateTime;
    @NotBlank(message = "Event location is required")
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

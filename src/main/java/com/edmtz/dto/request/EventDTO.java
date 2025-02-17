package com.edmtz.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventDTO {

    @NotBlank(message = "Event name is required")
    private String name;
    @NotBlank(message = "Event description is required")
    private String description;
    @NotNull(message = "Event datetime is required")
    private LocalDateTime dateTime;
    @NotBlank(message = "Event location is required")
    private String location;
    @NotBlank(message = "Event type (isFree) is required")
    private Boolean isFree;
    @DecimalMin(value = "0.0", message = "Price must be a positive number")
    public BigDecimal price;

    @AssertTrue(message = "Price is required when the event is paid")
    public boolean isValidPrice() {
        return isFree || (price != null && price.compareTo(BigDecimal.ZERO) >= 0);
    }

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

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

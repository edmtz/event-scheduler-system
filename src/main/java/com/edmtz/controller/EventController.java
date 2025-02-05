package com.edmtz.controller;

import com.edmtz.dto.request.EventDTO;
import com.edmtz.dto.response.EventResponseDTO;
import com.edmtz.model.Event;
import com.edmtz.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventDTO eventDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Event createdEvent = eventService.createEvent(eventDTO, userDetails);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body("Event Created: " + createdEvent.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable("id") Long id) {
        EventResponseDTO event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable("id") Long id, @RequestBody EventDTO updatedEvent) {
        eventService.updateEvent(id, updatedEvent);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}

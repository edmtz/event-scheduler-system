package com.edmtz.controller;

import com.edmtz.dto.EventDTO;
import com.edmtz.model.Event;
import com.edmtz.model.User;
import com.edmtz.repository.UserRepository;
import com.edmtz.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {

        User creator = userRepo.findById(eventDTO.getCreatedBy()).orElseThrow(() -> new RuntimeException("NOT FOUND"));

        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDateTime(eventDTO.getDateTime());
        event.setLocation(eventDTO.getLocation());
        event.setCreatedBy(creator);

        try {
            Event createdEvent = eventService.createEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED).body("Event Created: " + createdEvent.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }

    @GetMapping
    public String getAllEvents() {
        return "YEP";
    }
//
//    @GetMapping("/{id}") // PATH VARIABLE
//    public String getEventById(@PathVariable("id") int id) {
//        return "Event " + id;
//    }

    @PutMapping("/{id}")
    public String updateEvent(@PathVariable("id") Long id, @RequestBody String event) {
        return "Event " + id + " updated to" + event;
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        return "Event " + id + " deleted";
    }

}

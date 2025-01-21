package com.edmtz.controller;

import com.edmtz.model.Event;
import com.edmtz.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
//    @Autowired
//    private EventService eventService;

//    @PostMapping
//    public Event createEvent(@RequestBody Event event) {
//        return eventService.createEvent(event);
//    }

    @GetMapping
    public String getAllEvents() {
        return "YEP";
    }

    @GetMapping("/{id}") // PATH VARIABLE
    public String getEventById(@PathVariable("id") int id) {
        return "Event " + id;
    }

    @PostMapping
    public String createEvent(@RequestBody String event) {
        return "Created event: " + event;
    }

    @PutMapping("/{id}")
    public String updateEvent(@PathVariable("id") Long id, @RequestBody String event) {
        return "Event " + id + " updated to" + event;
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        return "Event " + id + " deleted";
    }

}

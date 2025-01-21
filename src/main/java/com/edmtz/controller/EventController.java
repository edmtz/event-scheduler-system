package com.edmtz.controller;

import com.edmtz.model.Event;
import com.edmtz.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {
//    @Autowired
//    private EventService eventService;

//    @PostMapping
//    public Event createEvent(@RequestBody Event event) {
//        return eventService.createEvent(event);
//    }

    @GetMapping("events")
    public String getAllEvents() {
        return "YEP";
    }

}

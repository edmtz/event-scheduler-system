package com.edmtz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {

    @GetMapping("/events")
    public String getEvents() {
        return "My Events Are Here!";
    }
}
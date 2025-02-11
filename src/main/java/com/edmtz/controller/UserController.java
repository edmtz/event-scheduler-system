package com.edmtz.controller;

import com.edmtz.model.User;
import com.edmtz.repository.UserRepository;
import com.edmtz.service.ParticipantService;
import com.edmtz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/events/{eventId}/join")
    public ResponseEntity<String> joinEvent(@PathVariable("eventId") Long eventId) {
        participantService.addParticipant(eventId, null);
        return ResponseEntity.ok().body("Participant added successfully!");
    }

    @DeleteMapping("/events/{eventId}/leave")
    public ResponseEntity<String> leaveEvent(@PathVariable("eventId") Long eventId) {
        participantService.removeParticipant(eventId, null);
        return ResponseEntity.ok("Participant deleted successfully");
    }
}

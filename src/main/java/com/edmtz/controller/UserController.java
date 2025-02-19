package com.edmtz.controller;

import com.edmtz.dto.request.UserDTO;
import com.edmtz.model.User;
import com.edmtz.service.ParticipantService;
import com.edmtz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        userService.updateUser(userId, userDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/events/{eventId}/join")
    public ResponseEntity<String> joinEvent(@PathVariable("eventId") Long eventId) {
        userService.joinEvent(eventId);
        return ResponseEntity.ok().body("Participant added successfully!");
    }

    @DeleteMapping("/events/{eventId}/leave")
    public ResponseEntity<String> leaveEvent(@PathVariable("eventId") Long eventId) {
        userService.leaveEvent(eventId);
        return ResponseEntity.ok("Participant deleted successfully");
    }
}

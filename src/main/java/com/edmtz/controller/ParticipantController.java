package com.edmtz.controller;

import com.edmtz.dto.response.ParticipantResponseDTO;
import com.edmtz.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/participants/events")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/{eventId}")
    public ResponseEntity<List<ParticipantResponseDTO>> getAllParticipants(@PathVariable("eventId") Long eventId) {
        List<ParticipantResponseDTO> participants = participantService.getAllParticipants(eventId);
        return ResponseEntity.ok(participants);
    }

    @PostMapping("/{eventId}/users/{userId}")
    public ResponseEntity<String> addParticipant(
            @PathVariable("eventId") Long eventId,
            @PathVariable("userId") Long userId) {
        participantService.addParticipant(eventId, userId);
        return ResponseEntity.ok().body("Participant added successfully!");
    }

    @DeleteMapping("/{eventId}/users/{userId}")
    public ResponseEntity<String> removeParticipant(
            @PathVariable("eventId") Long eventId,
            @PathVariable("userId") Long userId) {
        participantService.removeParticipant(eventId, userId);
        return ResponseEntity.ok().body("ok");
    }

}

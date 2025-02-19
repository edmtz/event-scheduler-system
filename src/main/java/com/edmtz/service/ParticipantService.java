package com.edmtz.service;

import com.edmtz.dto.response.ParticipantResponseDTO;
import com.edmtz.model.Event;
import com.edmtz.model.User;
import com.edmtz.repository.EventRepository;
import com.edmtz.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ParticipantService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<ParticipantResponseDTO> getAllParticipants(Long eventId) {
        Event event = eventRepository.findByIdWithParticipants(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return event.getParticipants().stream()
                .map(user -> new ParticipantResponseDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addParticipant(Long eventId, Long userId) {
        User requester = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToAdd = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Event event = eventRepository.findByIdWithParticipants(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        if (event.getParticipants().stream().map(User::getId).anyMatch(id -> id.equals(userToAdd.getId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already a participant in this event.");
        }
        if (!event.getCreatedBy().getId().equals(requester.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the event creator can add other users.");
        }
        event.getParticipants().add(userToAdd);
        eventRepository.save(event);
    }

    @Transactional
    public void removeParticipant(Long eventId, Long userId) {
        User requester = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToRemove = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Event event = eventRepository.findByIdWithParticipants(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        if (event.getParticipants().stream().map(User::getId).noneMatch(id -> id.equals(userToRemove.getId()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a participant in this event.");
        }
        if (!event.getCreatedBy().getId().equals(requester.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the event creator can remove other users.");
        }
        eventRepository.removeUserFromEvent(eventId, userId);
    }
}

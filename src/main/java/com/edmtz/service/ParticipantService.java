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
        User user = getUser(userId);
        Event event = eventRepository.findByIdWithParticipants(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        if (event.getParticipants().contains(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already a participant in this event.");
        }
        if (userId != null && !event.getCreatedBy().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the event creator can add other users.");
        }
        event.getParticipants().add(user);
        eventRepository.save(event);
    }


    @Transactional
    public void removeParticipant(Long eventId, Long userId) {
        User user = getUser(userId);
        userId = user.getId();
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        List<Long> participantIds = eventRepository.findParticipantIdsByEventId(eventId);
        if (!participantIds.contains(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a participant in this event.");
        }
        User eventCreator = event.getCreatedBy();
        if (!userId.equals(eventCreator.getId()) && !userId.equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only the event creator can remove other users.");
        }
        eventRepository.removeUserFromEvent(eventId, userId);
    }

    private User getUser(Long userId) {
        if (userId == null) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return (User) userDetails;
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

}

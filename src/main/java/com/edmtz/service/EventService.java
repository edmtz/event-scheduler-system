package com.edmtz.service;

import com.edmtz.config.exception.ResourceNotFoundException;
import com.edmtz.dto.request.EventDTO;
import com.edmtz.dto.response.EventResponseDTO;
import com.edmtz.model.Event;
import com.edmtz.model.User;
import com.edmtz.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventDTO eventDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User creator = (User) userDetails;
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDateTime(eventDTO.getDateTime());
        event.setLocation(eventDTO.getLocation());
        event.setCreatedBy(creator);
        return eventRepository.save(event);
    }

    public EventResponseDTO getEventById(Long id) {
        return eventRepository.findById(id).map(EventResponseDTO::new).orElseThrow(
                () -> new ResourceNotFoundException("Event not found with id: " + id));

    }

    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void updateEvent(Long id, EventDTO updatedEvent) {
        Event event = getEventOrThrow(id);
        validateUserOwnership(event);

        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setDateTime(updatedEvent.getDateTime());
        event.setLocation(updatedEvent.getLocation());

        eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = getEventOrThrow(id);
        validateUserOwnership(event);
        eventRepository.deleteById(id);
    }

    public Event getEventOrThrow(Long id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    public void validateUserOwnership(Event event) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userDetails.getUsername().equals(event.getCreatedBy().getUsername())) {
            throw new AccessDeniedException("Action denied. This user is not the owner.");
        }
    }
}
